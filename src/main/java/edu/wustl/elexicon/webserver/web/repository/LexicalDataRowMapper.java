package edu.wustl.elexicon.webserver.web.repository;

import edu.wustl.elexicon.webserver.web.LexicalDataViewModelMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

public class LexicalDataRowMapper implements RowMapper<Map<String, String>> {

    private static final DecimalFormat DF0 = new DecimalFormat("#,###");
    private static final DecimalFormat DF1 = new DecimalFormat("#,##0.0");
    private static final DecimalFormat DF2 = new DecimalFormat("#,##0.00");
    private static final DecimalFormat DF3 = new DecimalFormat("#,##0.000");


    @Override
    public Map<String, String> mapRow(ResultSet resultSet, int i) throws SQLException {
        Map<String, String> row = new LinkedHashMap<>();
        for (LexicalDataViewModelMapper mapper : LexicalDataViewModelMapper.values()) {
            String object = getObject(resultSet, mapper.getColumnName());
            if (object != null) {
                row.put(mapper.getFieldName(), object);
            }
        }
        return row;
    }

    private String getObject(ResultSet resultSet, String columnLabel) throws SQLException {
        if (hasColumn(resultSet, columnLabel)) {
            Object object = resultSet.getObject(columnLabel);
            if (object == null) {
                return "#";
            }
            if (object instanceof Integer) {
                return DF0.format(object);
            }
            if (columnLabel.equals("d_zscore")) {
                return DF3.format(object);
            }
            if (object instanceof Double) {
                return DF3.format(object);
            }
            return object.toString();
        } else {
            return null;
        }
    }

    private static boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columns = rsmd.getColumnCount();
        for (int x = 1; x <= columns; x++) {
            if (columnName.toLowerCase().equals(rsmd.getColumnName(x).toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}
