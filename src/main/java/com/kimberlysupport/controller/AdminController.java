package com.kimberlysupport.controller;

import com.kimberlysupport.service.SerialNmbrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private SerialNmbrService serialNmberService;

    @GetMapping({"","/complaint-list"})
    public String adminDashboard(){
        return "admin/complains";
    }

    @GetMapping("/qrcodes")
    public String qrcodes(HttpServletRequest request, Model model){
        model.addAttribute("urls",serialNmberService.generateNmbers(request));
        return "admin/qrcodes";
    }

}
