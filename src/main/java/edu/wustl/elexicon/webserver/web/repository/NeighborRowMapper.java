package edu.wustl.elexicon.webserver.web.repository;

import edu.wustl.elexicon.webserver.web.ItemViewModelMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

public class NeighborRowMapper implements RowMapper<Map<String, String>> {

    @Override
    public Map<String, String> mapRow(ResultSet resultSet, int i) throws SQLException {
        Map<String, String> row = new LinkedHashMap<>();
        for (ItemViewModelMapper mapper : ItemViewModelMapper.values()) {
            String object = "";
            if (object != null) {
                row.put(mapper.getFieldName(), object);
            }
        }
        return row;
    }

}
