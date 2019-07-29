package edu.wustl.elexicon.webserver.web.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model){
        model.addAttribute("errorMessage", "Something went wrong.  If this continues - please notify the administrator.");
        return "generalerror";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
