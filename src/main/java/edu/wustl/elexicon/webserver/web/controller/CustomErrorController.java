package edu.wustl.elexicon.webserver.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CustomErrorController implements ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(CustomErrorController.class);

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model, HttpSession session){
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        String sessionTrxId = (String) session.getAttribute("TRX_ID");
        String trxID = (sessionTrxId == null) ? "UNKNOWN" : sessionTrxId;
        logger.error("Session Id: " + trxID + " Error: " + exception.getMessage());
        model.addAttribute("errorMessage", "Something went wrong.  If this continues - please notify the administrator.");
        model.addAttribute("trxId", trxID);
        return "generalerror";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
