package com.softuni.Restaurant.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageMapController {

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/resturant1")
    public String r1() {
        return "resturant1";
    }

    @GetMapping("/resturant2")
    public String r2() {
        return "resturant2";
    }


}
