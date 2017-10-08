package com.github.serserser.springwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping(path = "/")
    public String indexPage() {
        return "application";
    }
}
