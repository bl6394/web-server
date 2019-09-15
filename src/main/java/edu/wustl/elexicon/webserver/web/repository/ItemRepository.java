package edu.wustl.elexicon.webserver.web.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ItemRepository {

    private final Logger log = LoggerFactory.getLogger(ItemRepository.class);

    private JdbcTemplate jdbcTemplate;

    public ItemRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, String>> get(String sql){
        return jdbcTemplate.query(sql, new ItemRowMapper());
    }

    public int getSize(String sql){
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

}
