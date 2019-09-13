package edu.wustl.elexicon.webserver.web.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.wustl.elexicon.webserver.web.NonWordItemViewModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NonWordItemRepository {
    private final Logger log = LoggerFactory.getLogger(NonWordItemRepository.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    private JdbcTemplate jdbcTemplate;

    public NonWordItemRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, String>> get(String trxId, List<String> fieldNames, List<String> criteria){
        String sql = "select " + createSelectList(fieldNames) +" from nw_item " + createCriteriaExpression(criteria);
        log.info("Session Id: " + trxId + " SQL: " + sql);
        return jdbcTemplate.query(sql, new NonWordItemRowMapper());
    }

    List<String> createColumnHeaderList(List<String> fieldNames){
        List<String> columnHeaders = new ArrayList<>();
        for (NonWordItemViewModelMapper item : NonWordItemViewModelMapper.values()){
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
        for (NonWordItemViewModelMapper item : NonWordItemViewModelMapper.values()){
            if (fieldNames.contains(item.getFieldName()) ){
                columns.append(item.getColumnName());
                columns.append(", ");
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
                    NonWordItemViewModelMapper field = NonWordItemViewModelMapper.getByMinConstraint(entry.getKey());
                    String value = entry.getValue();
                    if ((field != null) && (value != null)) {
                        whereClause.append(" " + field.getFieldName() + " >= " + Double.parseDouble(value) + " and");
                    }
                } else if (entry.getKey().startsWith("max")) {
                    NonWordItemViewModelMapper field = NonWordItemViewModelMapper.getByMaxConstraint(entry.getKey());
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
            log.error("error", e);
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
                log.error("error", e);
                return map;
            }
        }
    }
}
