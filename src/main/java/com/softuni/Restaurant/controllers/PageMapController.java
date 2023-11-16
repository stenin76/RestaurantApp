package com.softuni.Restaurant.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageMapController {

    @GetMapping("/about.html")
    public String about() {

        return "about.html";
    }

    @GetMapping("/resturant1.html")
    public String r1() {

        return "resturant1.html";
    }

    @GetMapping("/resturant2.html")
    public String r2() {

        return "resturant2.html";
    }
}
