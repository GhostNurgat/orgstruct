package ru.brusnika.orgstruct.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@RestController
public class IndexController implements ErrorController {
    private static final String PATH = "/error";

    @RequestMapping(PATH)
    public ModelAndView saveLeadQuery() {
        return new ModelAndView("forward:/");
    }
    
    public String getErrorPath() {
        return PATH;
    }
}
