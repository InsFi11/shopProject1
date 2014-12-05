package com.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/*
----------------
НЕ ИСПОЛЬЗОВАТЬ ГЛОБАЛЬНЫЙ ПЕРЕМЕННЫЕ
----------------
*/


@Controller
public class VovaController {
    @RequestMapping(value = "/")
    public String getIndexPage(){
        return "redirect:prevIndex";
    }
}
