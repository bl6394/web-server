package edu.wustl.elexicon.webserver.web.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.wustl.elexicon.webserver.web.NamingDataViewModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NamingDataRepository {
    private final Logger log = LoggerFactory.getLogger(NamingDataRepository.class);

    private JdbcTemplate jdbcTemplate;

    public NamingDataRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, String>> get(String sql){
        return jdbcTemplate.query(sql, new NamingDataRowMapper());
    }

    public int getSize(String sql){
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

}
