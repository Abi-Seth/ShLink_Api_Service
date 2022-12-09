package com.shlink.shlink.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShlinkEntry {

    @GetMapping("/")
    public String ApplicationEntry() {
        return "Welcome to Shlink Application";
    }
}
