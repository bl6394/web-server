package edu.wustl.elexicon.webserver.web.repository;

import edu.wustl.elexicon.webserver.web.ArbitraryViewModelMapper;
import edu.wustl.elexicon.webserver.web.ItemViewModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ArbitrarySQLHelper {

    private final Logger log = LoggerFactory.getLogger(ArbitrarySQLHelper.class);

    public String getSQL( List<String> fieldNames){
        return  "select " + createSelectList(fieldNames) +" from arb_nw_item";
    }


    private String createSelectList(List<String> fieldNames){
        if (fieldNames == null || fieldNames.isEmpty()){
            return "Word";
        }
        StringBuilder columns = new StringBuilder("Word, ");
        for (ArbitraryViewModelMapper item : ArbitraryViewModelMapper.values()){
            if (fieldNames.contains(item.getFieldName()) ){
                columns.append(item.getColumnName());
                columns.append(", ");
            }
        }
        columns.delete(columns.length() -2, columns.length());
        return columns.toString();
    }



}
