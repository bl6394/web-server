package edu.wustl.elexicon.webserver.web.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NonWordItemRepository {
    private final Logger log = LoggerFactory.getLogger(NonWordItemRepository.class);

    private JdbcTemplate jdbcTemplate;

    public NonWordItemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Map<String, String>> get(String sql) {
        return jdbcTemplate.query(sql, new NonWordItemRowMapper());
    }

    public int getSize(String sql) {
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }


}
