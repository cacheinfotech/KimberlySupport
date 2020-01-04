package com.kimberlysupport.controller;

import com.kimberlysupport.model.Complain;
import com.kimberlysupport.model.Dispenser;
import com.kimberlysupport.service.SerialNmbrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class RequestController {
    @Autowired
    private SerialNmbrService serialNmbrService;

    @RequestMapping("")
    public String index(){
        return "index";
    }

    @RequestMapping("/dashboard")
    public ModelAndView dashboard(Principal principal) {
        String role = principal.toString().substring(principal.toString().lastIndexOf(":") + 1)
                .trim().toLowerCase();
        return new ModelAndView("redirect:/" + role);
    }

    @GetMapping("/support")
    public ModelAndView support(@RequestParam("srnmbr") String srnmbr){
        return serialNmbrService.getSerialDetails(srnmbr);
    }

    @PostMapping("/fileComplain")
    public ModelAndView fileComplain(Complain complain, Dispenser dispenser){
        return serialNmbrService.fileComplain(complain,dispenser);
    }

}
