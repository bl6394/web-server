package edu.wustl.elexicon.webserver.web.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NeighborRepository {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final DecimalFormat DF0 = new DecimalFormat("#,###");

    private JdbcTemplate jdbcTemplate;

    public NeighborRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, String>> get(String word, String type, String targetDb, Boolean withPron) {
        String targetNeighborDb;
        switch (type) {
            case "neighbors":
                targetNeighborDb = targetDb.equals("item") ? "ortho_n" : "ortho_n_plus";
                break;
            case "phono":
                targetNeighborDb = targetDb.equals("item") ? "phono_n" : "phono_n_plus";
                break;
            case "phonoh":
                targetNeighborDb = targetDb.equals("item") ? "phonoh_n" : "phonoh_n_plus";
                break;
            case "og":
                targetNeighborDb = targetDb.equals("item") ? "og_n" : "og_n_plus";
                break;
            case "ogh":
                targetNeighborDb = targetDb.equals("item") ? "ogh_n" : "ogh_n_plus";
                break;
            default:
                throw new IllegalArgumentException("invalid parameter");
        }
        String sql =  (withPron ? "SELECT word, pron, freq_hal from " : "SELECT word, freq_hal from ")  + targetDb + " where wid in (Select n_wid from " + targetNeighborDb + " where wid in (select wid from " + targetDb + " where word = ?) ) order by freq_hal";
        NeighborRowCallbackHandler neighborRowCallbackHandler = new NeighborRowCallbackHandler(withPron);
        jdbcTemplate.query(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws
                    SQLException {
                preparedStatement.setString(1, word);
            }
        }, neighborRowCallbackHandler);
        return neighborRowCallbackHandler.getNeighborList();
    }

    class NeighborRowCallbackHandler implements RowCallbackHandler {
        private List<Map<String, String>> aList;
        private Boolean withPron;

        public NeighborRowCallbackHandler(Boolean withPron) {
            this.withPron = withPron;
            aList = new ArrayList<Map<String, String>>();
        }

        public void processRow(ResultSet rs) throws SQLException {
            Map<String, String> map = new HashMap<>();
            String word = rs.getString("word");
            map.put("Word", word);
            if (withPron){
                map.put("Pron", rs.getString("pron"));
            }
            int freqHal = rs.getInt("freq_hal");
            map.put("Freq_Hal", DF0.format(freqHal));
            aList.add(map);
        }

        private List<Map<String, String>> getNeighborList() {
            return aList;
        }
    }


}
