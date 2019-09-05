package edu.wustl.elexicon.webserver.web.controller;

import edu.wustl.elexicon.webserver.web.repository.NeighborRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class NeighborController {

    private final Logger log = LoggerFactory.getLogger(NeighborController.class);

    private NeighborRepository neighborRepository;

    public NeighborController(NeighborRepository neighborRepository){
        this.neighborRepository = neighborRepository;
    }

    @GetMapping (value = "/neighbors")
    public String process(@RequestParam Map<String,String> allRequestParams, Model model) {
        String word = allRequestParams.get("word");
        setWord(model, word);
        String type = allRequestParams.get("type");
        setType(model, type);
        String target = allRequestParams.get("target");
        String targetDb = target.equals("Restricted") ? "item" : "itemplus";
        Boolean withPron = !"neighbors".equalsIgnoreCase(type);
        List<Map<String, String>> query = neighborRepository.getOne(word, type, targetDb, withPron );
        model.addAttribute("items", query);
        model.addAttribute("queryEmpty", query == null || query.isEmpty() ?  "True" : "False");
        return "neighbors";
    }

    private void setWord(Model model, String word) {
        if (word != null) {
            model.addAttribute("neighborword", word);
        } else {
            throw new IllegalArgumentException("invalid parameter");
        }
    }

    private void setType(Model model, String type) {
        switch (type) {
            case "neighbors":
                model.addAttribute("title", "Orthographic Neighbors");
                model.addAttribute("heading", "Orthographic Neighborhood");
                break;
            case "phono":
                model.addAttribute("title", "Phonological Neighbors");
                model.addAttribute("heading", "Phonological Neighborhood");
                break;
            case "phonoh":
                model.addAttribute("title", "Phonological Neighbors (Homophones)");
                model.addAttribute("heading", "Phonological Neighborhood (Homophones)");
                break;
            case "og":
                model.addAttribute("title", "Phonographic Neighbors");
                model.addAttribute("heading", "Phonographic Neighborhood");
                break;
            case "ogh":
                model.addAttribute("title", "Phonographic Neighbors (Homophones)");
                model.addAttribute("heading", "Phonographic Neighborhood (Homophones)");
                break;
            default:
                throw new IllegalArgumentException("invalid parameter");
        }
    }


}

