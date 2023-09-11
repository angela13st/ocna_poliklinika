package com.example.ocna_poliklinika.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class StraniceController {

    @RequestMapping("/index")
    public String prikaziIndex() {
        return "index";
    }


    @RequestMapping("/narucivanje")
    public String prikaziNarucivanje() {
        return "narucivanje";
    }

    @RequestMapping("/pacijent")
    public String prikaziPacijent() {
        return "pacijent";
    }
}
