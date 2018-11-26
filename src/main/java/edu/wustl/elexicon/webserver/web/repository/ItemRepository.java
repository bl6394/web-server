package edu.wustl.elexicon.webserver.web.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

@Service
public class ItemRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void get(){

        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select * from item");
        System.out.println("trest");
    }
}
