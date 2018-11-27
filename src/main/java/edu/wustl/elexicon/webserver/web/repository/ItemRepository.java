package edu.wustl.elexicon.webserver.web.repository;

import edu.wustl.elexicon.webserver.web.domain.Item;
import edu.wustl.elexicon.webserver.web.domain.ItemRowMapper;
import edu.wustl.elexicon.webserver.web.domain.MappedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void get(){

        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select * from item");
        List<Item> items = jdbcTemplate.query("select * from item", new BeanPropertyRowMapper(Item.class));
        List<MappedItem> items1 = jdbcTemplate.query("select * from item", new ItemRowMapper() );
        System.out.println("trest");
    }
}
