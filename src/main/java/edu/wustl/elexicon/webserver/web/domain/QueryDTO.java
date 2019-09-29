package edu.wustl.elexicon.webserver.web.domain;

import java.util.List;
import java.util.Map;

public class QueryDTO {

    public List<Map<String, String>> query;
    public List<Map<String, String>> summary;
    public List<Map<String, String>> notFound;
    public List<Map<String, String>> neighborhood;

}
