package edu.wustl.elexicon.webserver.web.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.wustl.elexicon.webserver.web.ItemViewModelMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TempTableRepository {

    private static final ObjectMapper mapper = new ObjectMapper();

    private JdbcTemplate jdbcTemplate;

    public TempTableRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void get(List<String> words){
        jdbcTemplate.execute("drop table if exists bjorntable;");
        jdbcTemplate.execute("create table bjorntable (word VARCHAR(50) NOT NULL);");
        jdbcTemplate.execute("insert into bjorntable values ('test')");
        return;
    }


}
