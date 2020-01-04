package com.kimberlysupport.controller;

import com.kimberlysupport.repository.DispenserRepository;
import com.kimberlysupport.service.SerialNmbrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private SerialNmbrService serialNmberService;
    @Autowired
    private DispenserRepository dispenserRepository;

    @GetMapping({"","/complaint-list"})
    public String adminDashboard(){
        return "admin/complains";
    }

    @GetMapping("/qrcodes")
    public String qrcodes(HttpServletRequest request, Model model){
        model.addAttribute("urls",serialNmberService.generateNmbers(request));
        return "admin/qrcodes";
    }

    @GetMapping("/device-list")
    public String devicelist(Model model){
        model.addAttribute("devices",dispenserRepository.findAll());
        return "admin/devlist";
    }

}
