package edu.wustl.elexicon.webserver.web.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.wustl.elexicon.webserver.web.ItemViewModelMapper;
import edu.wustl.elexicon.webserver.web.domain.QueryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TempTableRepository {

    private final Logger log = LoggerFactory.getLogger(TempTableRepository.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    private JdbcTemplate jdbcTemplate;

    public TempTableRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public QueryDTO get(String trxId, List<String> words, List<String> fieldNames, String targetDb){
        QueryDTO queryDTO = new QueryDTO();
        createTempSubmissionTable(words);
        queryDTO.query = createQueryResponse(trxId,  fieldNames, targetDb);
        queryDTO.summary = createSummmaryResponse(fieldNames, targetDb);
        queryDTO.notFound = createNotFoundResponse(fieldNames, targetDb);
        return queryDTO;
    }

    private List<Map<String, String>> createQueryResponse(String  trxId, List<String> fieldNames, String targetDb) {
        String sql = "select s.occurrences as Occurrences, " + createSelectList(fieldNames) +" from " + targetDb + " as target INNER JOIN submission as s on target.word = s.tempword ORDER by s.occurrences";
        log.info("Session Id: " + trxId + "SQL: " + sql);
        return jdbcTemplate.query(sql, new ItemRowMapper());
    }

    private List<Map<String, String>> createSummmaryResponse(List<String> fieldNames, String targetDb) {
        String summarySelectList = createSummarySelectList(fieldNames);
        String sql = "select " + createSummarySelectList(fieldNames) +" from " + targetDb + " as target INNER JOIN submission as s on target.word = s.tempword";
        return jdbcTemplate.query(sql, new SummaryRowMapper());
    }

    private List<Map<String, String>> createNotFoundResponse(List<String> fieldNames, String targetDb) {
        String sql = "select s.tempword from submission as s LEFT JOIN " + targetDb + " as target  on s.tempword = target.word WHERE target.word is NULL";
        return jdbcTemplate.query(sql, new NotFoundRowMapper());
    }

    private void createTempSubmissionTable(List<String> words) {
        jdbcTemplate.execute("drop temporary table if exists submission;");
        jdbcTemplate.execute("create temporary table submission (tempword VARCHAR(50) NOT NULL, PRIMARY KEY (tempword), occurrences int (11));");
        for(String word: words){
            jdbcTemplate.update("insert into submission values (?, ?) ON DUPLICATE KEY UPDATE tempword = tempword, occurrences = occurrences + 1 ;", word,1);
        }
    }

    private List<String> createColumnHeaderList(List<String> fieldNames){
        List<String> columnHeaders = new ArrayList<>();
        for (ItemViewModelMapper item : ItemViewModelMapper.values()){
            if (fieldNames.contains(item.getFieldName()) ){
                columnHeaders.add(item.getFieldName());
            }
        }
        return columnHeaders;
    }

    private String createSelectList(List<String> fieldNames){
        if (fieldNames == null || fieldNames.isEmpty()){
            return "Word";
        }
        StringBuilder columns = new StringBuilder("Word, ");
        for (ItemViewModelMapper item : ItemViewModelMapper.values()){
            if (fieldNames.contains(item.getFieldName()) ){
                columns.append(item.getColumnName());
                columns.append(", ");
            }
        }
        columns.delete(columns.length() -2, columns.length());
        return columns.toString();
    }

    private String createSummarySelectList(List<String> fieldNames){

        StringBuilder columns = new StringBuilder();
        for (ItemViewModelMapper item : ItemViewModelMapper.values()){
            if (fieldNames.contains(item.getFieldName()) ){
                String columnName = item.getColumnName();
                switch(columnName){
                    case "pron":
                    case "morphsp":
                    case "morphpr":
                    case "pos":
                    case "word":
                            break;
                    default:
                        columns.append("AVG(" + columnName + ") as mean_" + columnName + ", " );
                }
            }
        }
        columns.delete(columns.length() -2, columns.length());
        return columns.toString();
    }

    private String createCriteriaExpression(List<String> constraints){
        try {
            Map<String, String> map = parseConstraints(constraints);
            StringBuilder whereClause = new StringBuilder(" WHERE ");
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getKey().startsWith("min")) {
                    ItemViewModelMapper field = ItemViewModelMapper.getByMinConstraint(entry.getKey());
                    String value = entry.getValue();
                    if ((field != null) && (value != null)) {
                        whereClause.append(" " + field.getFieldName() + " >= " + Double.parseDouble(value) + " and");
                    }
                } else if (entry.getKey().startsWith("max")) {
                    ItemViewModelMapper field = ItemViewModelMapper.getByMaxConstraint(entry.getKey());
                    String value = entry.getValue();
                    if ((field != null) && (value != null)) {
                        whereClause.append(" " + field.getFieldName() + " <= " + Double.parseDouble(value) + " and");
                    }
                } else {
                    ;  // skip bad data
                }
            }
            whereClause.delete(whereClause.length() - 4, whereClause.length());
            String result = whereClause.toString();
            return result.equals(" WHERE ") ? "" : result;
        }
        catch (Exception e){
            log.info("error" , e);
            return "";
        }
    }

    private Map<String, String> parseConstraints(List<String> constraints){
        Map<String, String> map = new HashMap<>();
        if (constraints.isEmpty()) {
            return map;
        } else {
            try {
                String json = constraints.get(0);
                return mapper.readValue(json, HashMap.class);
            } catch (Exception e) {
                log.info("error" , e);
                return map;
            }
        }
    }


}
