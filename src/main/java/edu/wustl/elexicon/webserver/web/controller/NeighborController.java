package edu.wustl.elexicon.webserver.web.controller;

import edu.wustl.elexicon.webserver.web.repository.NeighborRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class NeighborController {

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
        List<Map<String, String>> query = neighborRepository.get(word, type, targetDb);
        model.addAttribute("items", query);
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
                break;
            case "phono":
                model.addAttribute("title", "Phonological Neighbors");
                break;
            case "phonoh":
                model.addAttribute("title", "Phonological Neighbors (Homophones)");
                break;
            case "og":
                model.addAttribute("title", "Phonographic Neighbors");
                break;
            case "ogh":
                model.addAttribute("title", "Phonographic Neighbors (Homophones)");
                break;
            default:
                throw new IllegalArgumentException("invalid parameter");
        }
    }


}

