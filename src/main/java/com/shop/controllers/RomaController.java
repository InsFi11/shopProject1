package com.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



/*
----------------
НЕ ИСПОЛЬЗОВАТЬ ГЛОБАЛЬНЫЙ ПЕРЕМЕННЫЕ
----------------
*/


@Controller
public class RomaController {
    @RequestMapping(value = "/prevIndex")
    public String getIndexPage(ModelMap model){
        model.addAttribute("message", "Стартовая страница");
        return "index";
    }
}
