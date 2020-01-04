package com.kimberlysupport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {


    @GetMapping("/scanner")
    public String scanner(){
        return "user/scanner";
    }

    @GetMapping("")
    public String dashboard(){
    return "user/addDevice";
    }

    @GetMapping("/addDevice")
    public String addDevice(@RequestParam("srnmbr") String srnmbr, Model model){
        model.addAttribute("srnmbr",srnmbr);
        return "user/addDevice";
    }


}
