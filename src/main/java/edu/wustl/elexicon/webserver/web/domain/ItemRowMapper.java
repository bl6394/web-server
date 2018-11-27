package edu.wustl.elexicon.webserver.web.domain;

import org.springframework.jdbc.core.RowMapper;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ItemRowMapper implements RowMapper<MappedItem> {
    @Override
    public MappedItem mapRow(ResultSet resultSet, int i) throws SQLException {
        MappedItem mappedItem = new MappedItem();
        mappedItem.setWord(getObject(resultSet, "word"));
        mappedItem.setWid(getObject(resultSet, "wid"));
        mappedItem.setLength(getObject(resultSet,"length"));
        mappedItem.setFreqKF(getObject(resultSet,"freq_kf"));
        mappedItem.setFreqTsa(getObject(resultSet,"freq_tsa"));
        mappedItem.setLogFreqHal(getObject(resultSet,"log_freq_hal"));
        mappedItem.setOrthoNeighborhood(getObject(resultSet,"ortho_n"));
        mappedItem.setOld(getObject(resultSet,"old"));
        mappedItem.setOgN(getObject(resultSet,"og_n"));
        mappedItem.setOgNH(getObject(resultSet,"og_n_h"));
        mappedItem.setOldf(getObject(resultSet,"oldf"));
        mappedItem.setFreqN(getObject(resultSet,"freq_n"));
        mappedItem.setFreqNP(getObject(resultSet,"freq_n_p"));
        mappedItem.setFreqNPH(getObject(resultSet,"freq_n_ph"));
        mappedItem.setFreqNOG(getObject(resultSet,"freq_n_og"));
        mappedItem.setFreqNOGH(getObject(resultSet,"freq_n_ogh"));
        mappedItem.setFreqGreater(getObject(resultSet,"freq_greater"));
        mappedItem.setFreqGMean(getObject(resultSet,"freq_g_mean"));
        mappedItem.setFreqLess(getObject(resultSet,"freq_less"));
        mappedItem.setFreqLMean(getObject(resultSet,"freq_l_mean"));
        mappedItem.setFreqRel(getObject(resultSet,"freq_rel"));
        mappedItem.setBgSum(getObject(resultSet,"bg_sum"));
        mappedItem.setBgMean(getObject(resultSet,"bg_mean"));
        mappedItem.setBgFreqByPos(getObject(resultSet,"bg_freq_by_pos"));
        mappedItem.setiMeanRt(getObject(resultSet,"i_mean_rt"));
        mappedItem.setIrtn(getObject(resultSet,"i_rt_n"));
        mappedItem.setiZscore(getObject(resultSet,"i_zscore"));
        mappedItem.setIsd(getObject(resultSet,"i_sd"));
        mappedItem.setObs(getObject(resultSet,"obs"));
        mappedItem.setiMeanAccuracy(getObject(resultSet,"i_mean_accuracy"));
        mappedItem.setiNmgMeanRt(getObject(resultSet,"i_nmg_mean_rt"));
        mappedItem.setiNmgRtN(getObject(resultSet,"i_nmg_rt_n"));
        mappedItem.setiNmgZscore(getObject(resultSet,"i_nmg_zscore"));
        mappedItem.setiNmgSd(getObject(resultSet,"i_nmg_sd"));
        mappedItem.setiNmgObs(getObject(resultSet,"i_nmg_obs"));
        mappedItem.setiNmgMeanAccuracy(getObject(resultSet,"i_nmg_mean_accuracy"));
        mappedItem.setPhonology(getObject(resultSet,"phonology"));
        mappedItem.setPhonemecnt(getObject(resultSet,"phonemecnt"));
        mappedItem.setSyntacticcode(getObject(resultSet,"syntacticcode"));
        mappedItem.setHyphenation(getObject(resultSet,"hyphenation"));
        mappedItem.setHyphencnt(getObject(resultSet,"hyphencnt"));
        mappedItem.setRef(getObject(resultSet,"ref"));
        mappedItem.setPos(getObject(resultSet,"pos"));
        mappedItem.setInfl(getObject(resultSet,"infl"));
        mappedItem.setPron(getObject(resultSet,"pron"));
        mappedItem.setPhono_n(getObject(resultSet,"phono_n"));
        mappedItem.setPld(getObject(resultSet,"pld"));
        mappedItem.setPldf(getObject(resultSet,"pldf"));
        mappedItem.setPhono_n_h(getObject(resultSet,"phono_n_h"));
        mappedItem.setNsyll(getObject(resultSet,"nsyll"));
        mappedItem.setNphon(getObject(resultSet,"nphon"));
        mappedItem.setNmorph(getObject(resultSet,"nmorph"));
        mappedItem.setMorphsp(getObject(resultSet,"morphsp"));
        mappedItem.setMorphpr(getObject(resultSet,"morphpr"));
        mappedItem.setSubtlwf(getObject(resultSet,"subtlwf"));
        mappedItem.setLgsubtlwf(getObject(resultSet,"lgsubtlwf"));
        mappedItem.setSubtlcd(getObject(resultSet,"subtlcd"));
        mappedItem.setLgsubtlcd(getObject(resultSet,"lgsubtlcd"));
        return mappedItem;
    }

    String getObject(ResultSet resultSet, String columnLabel) throws SQLException {
        if (hasColumn(resultSet, columnLabel)) {
            Object object = resultSet.getObject(columnLabel);
            return  object == null ? "#" : object.toString();
        } else {
            return null;
        }
    }

    public static boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columns = rsmd.getColumnCount();
        for (int x = 1; x <= columns; x++) {
            if (columnName.toLowerCase().equals(rsmd.getColumnName(x).toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}
