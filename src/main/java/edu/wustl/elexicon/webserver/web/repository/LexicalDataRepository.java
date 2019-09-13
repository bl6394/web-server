package edu.wustl.elexicon.webserver.web.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.wustl.elexicon.webserver.web.LexicalDataViewModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LexicalDataRepository {
    private final Logger log = LoggerFactory.getLogger(LexicalDataRepository.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    private JdbcTemplate jdbcTemplate;

    public LexicalDataRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, String>> get(String  trxId, List<String> fieldNames, List<String> criteria){
        String sql = "select " + createSelectList(fieldNames) +" from exp_data " + createCriteriaExpression(criteria);
        log.info("Session Id: " + trxId + " SQL: " + sql);
        return jdbcTemplate.query(sql, new LexicalDataRowMapper());
    }

    public int getSize(String  trxId, List<String> fieldNames, List<String> criteria){
        String sql = "select count(*) from exp_data " + createCriteriaExpression(criteria);
        log.info("Session Id: " + trxId + " SQL: " + sql);
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    List<String> createColumnHeaderList(List<String> fieldNames){
        List<String> columnHeaders = new ArrayList<>();
        for (LexicalDataViewModelMapper expData : LexicalDataViewModelMapper.values()){
            if (fieldNames.contains(expData.getFieldName()) ){
                columnHeaders.add(expData.getFieldName());
            }
        }
        return columnHeaders;
    }

    private String createSelectList(List<String> fieldNames){
        if (fieldNames == null || fieldNames.isEmpty()){
            return "";
        }
        StringBuilder columns = new StringBuilder();
        for (LexicalDataViewModelMapper expData : LexicalDataViewModelMapper.values()){
            if (fieldNames.contains(expData.getFieldName()) ){
                columns.append(expData.getColumnName());
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
                    LexicalDataViewModelMapper field = LexicalDataViewModelMapper.getByMinConstraint(entry.getKey());
                    String value = entry.getValue();
                    if ((field != null) && (value != null)) {
                        whereClause.append(" " + field.getFieldName() + " >= " + Double.parseDouble(value) + " and");
                    }
                } else if (entry.getKey().startsWith("max")) {
                    LexicalDataViewModelMapper field = LexicalDataViewModelMapper.getByMaxConstraint(entry.getKey());
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
            log.error("error, ", e);
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
                log.error("error, ", e);
                return map;
            }
        }
    }
}
