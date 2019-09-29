package edu.wustl.elexicon.webserver.web.repository;

import edu.wustl.elexicon.webserver.web.ArbitraryViewModelMapper;
import edu.wustl.elexicon.webserver.web.domain.QueryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ArbitraryTableRepository {
    private final Logger log = LoggerFactory.getLogger(ArbitraryTableRepository.class);

    private JdbcTemplate jdbcTemplate;

    public ArbitraryTableRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public QueryDTO get(String trxId, List<String> words, String sql, String targetDb){
        jdbcTemplate.execute("drop temporary table if exists bjorntable;");
        jdbcTemplate.execute("drop temporary table if exists arb_nw_item;");
        jdbcTemplate.execute("drop temporary table if exists arb_nw_ortho_n;");
        jdbcTemplate.execute("create temporary table bjorntable (tempword VARCHAR(50) NOT NULL, PRIMARY KEY (tempword));");
        jdbcTemplate.execute("create temporary table arb_nw_item (word VARCHAR(50) NOT NULL, " +
                "length tinyint DEFAULT NULL, ortho_n int(11) DEFAULT NULL, bg_sum int(11) DEFAULT NULL, " +
                "bg_mean float DEFAULT NULL, bg_freq_by_pos int(11) DEFAULT NULL );");
        jdbcTemplate.execute("create temporary table arb_nw_ortho_n (word VARCHAR(50) DEFAULT NULL, " +
                "ortho_word VARCHAR(50) DEFAULT NULL );");
        for(String word: words){
            jdbcTemplate.update("insert into bjorntable values (?) ON DUPLICATE KEY UPDATE tempword = tempword;", word);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        jdbcTemplate.execute("insert into arb_nw_item (word) SELECT tempword FROM bjorntable;");
        if ( "item".equalsIgnoreCase(targetDb)){
            jdbcTemplate.execute("CALL gen_arb_bigram('bigram');");
        } else{
            jdbcTemplate.execute("CALL gen_arb_bigram('bigramplus');");
        }
        QueryDTO queryDTO = new QueryDTO();
        queryDTO.query = jdbcTemplate.query(sql, new ArbitraryRowMapper());
        queryDTO.neighborhood = jdbcTemplate.query( "Select *  from arb_nw_ortho_n", new NeighborhoodRowMapper() );
        return queryDTO;
    }



}
