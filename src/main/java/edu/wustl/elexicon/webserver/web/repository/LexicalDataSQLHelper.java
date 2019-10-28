package edu.wustl.elexicon.webserver.web.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.wustl.elexicon.webserver.web.ItemViewModelMapper;
import edu.wustl.elexicon.webserver.web.LexicalDataViewModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LexicalDataSQLHelper {

    private final Logger log = LoggerFactory.getLogger(LexicalDataSQLHelper.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    public String getSQL( List<String> fieldNames, List<String> criteria){
        return  "select " + createSelectList(fieldNames) +" from exp_data " + createCriteriaExpression(criteria);
    }

    public String getSizeSQL(List<String> criteria){
        return  "select count(*) from exp_data " + createCriteriaExpression(criteria);
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
                        whereClause.append(" " + field.getColumnName() + " >= " + Double.parseDouble(value) + " and");
                    }
                } else if (entry.getKey().startsWith("max")) {
                    LexicalDataViewModelMapper field = LexicalDataViewModelMapper.getByMaxConstraint(entry.getKey());
                    String value = entry.getValue();
                    if ((field != null) && (value != null)) {
                        whereClause.append(" " + field.getColumnName() + " <= " + Double.parseDouble(value) + " and");
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
