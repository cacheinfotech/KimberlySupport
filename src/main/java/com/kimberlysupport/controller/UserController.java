package com.kimberlysupport.controller;

import com.kimberlysupport.model.Dispenser;
import com.kimberlysupport.service.SerialNmbrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SerialNmbrService serialNmbrService;

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

    @PostMapping("/addDevice")
    public ModelAndView registerDevice(Dispenser dispenser ){
        return serialNmbrService.registerDispenser(dispenser);
    }


}
