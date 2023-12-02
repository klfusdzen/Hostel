package com.hostel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomepageController {
    @GetMapping(value = "/")
    public String home() {
        return "home";
    }
}
