package com.Asad.url_shortner.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping("/yo")
    public String yo () {

        return "Asad bhai op";
    }
}
