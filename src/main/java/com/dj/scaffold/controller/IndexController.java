package com.dj.scaffold.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping("/")
    public ModelAndView index(ModelAndView mv) {
        mv.addObject("word",  "thymeleaf1");
        mv.setViewName("index");
        return mv;
    }

}

