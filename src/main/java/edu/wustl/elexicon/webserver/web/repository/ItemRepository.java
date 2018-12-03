package edu.wustl.elexicon.webserver.web.repository;

import edu.wustl.elexicon.webserver.web.ItemViewModelMapper;
import edu.wustl.elexicon.webserver.web.domain.ItemRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ItemRepository {

    private JdbcTemplate jdbcTemplate;

    public ItemRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, String>> get(List<String> fieldNames, String targetDb){
        String sql = "select " + createSelectList(fieldNames) +" from " + targetDb;
        System.out.println(sql);
        return jdbcTemplate.query(sql, new ItemRowMapper());
    }

    List<String> createColumnHeaderList(List<String> fieldNames){
        List<String> columnHeaders = new ArrayList<>();
        for (ItemViewModelMapper item : ItemViewModelMapper.values()){
            if (fieldNames.contains(item.getFieldName()) ){
                columnHeaders.add(item.getFieldName());
            }
        }
        return columnHeaders;
    }

    private String createSelectList(List<String> fieldNames){
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
}
