package edu.wustl.elexicon.webserver.web.repository;

import edu.wustl.elexicon.webserver.web.Items;
import edu.wustl.elexicon.webserver.web.domain.Item;
import edu.wustl.elexicon.webserver.web.domain.ItemRowMapper;
import edu.wustl.elexicon.webserver.web.domain.ItemRowMapper2;
import edu.wustl.elexicon.webserver.web.domain.MappedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<MappedItem> get(){

        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select * from item");
        return jdbcTemplate.query("select * from item", new ItemRowMapper() );
    }

    public List<List<String>> get2(List<String> fieldNames){
        String sql = "select " + createSelectList(fieldNames) +" from item";
        List<List<String>> query = jdbcTemplate.query(sql, new ItemRowMapper2());
        query.add(0,createColumnHeaderList(fieldNames));
        return  query;
    }

    List<String> createColumnHeaderList(List<String> fieldNames){
        List<String> columnHeaders = new ArrayList<>();
        for (Items item : Items.values()){
            if (fieldNames.contains(item.getFieldName()) ){
                columnHeaders.add(item.getFieldName());
            }
        }
        return columnHeaders;
    }

    String createSelectList(List<String> fieldNames){
        StringBuilder columns = new StringBuilder();
        for (Items item : Items.values()){
            if (fieldNames.contains(item.getFieldName()) ){
                columns.append(item.getColumnName());
                columns.append(", ");
            }
        }
        columns.delete(columns.length() -2, columns.length());
        return columns.toString();
    }
}
