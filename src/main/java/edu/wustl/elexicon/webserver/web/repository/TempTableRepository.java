package edu.wustl.elexicon.webserver.web.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.wustl.elexicon.webserver.web.ItemViewModelMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TempTableRepository {

    private static final ObjectMapper mapper = new ObjectMapper();

    private JdbcTemplate jdbcTemplate;

    public TempTableRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void get(){
        System.out.println("Start");
        jdbcTemplate.execute("drop TEMPORARY table if exists bjorntable;");
        jdbcTemplate.execute("create temporary table bjorntable (word VARCHAR(50) NOT NULL);");
        System.out.println("After CREATE");
        jdbcTemplate.execute("insert into bjorntable values ('test')");
        System.out.println("After insert");
        try {
            Thread.sleep(30000);
            System.out.println("After sleep");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return;
    }


}