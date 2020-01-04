package com.kimberlysupport.controller;

import com.kimberlysupport.model.User;
import com.kimberlysupport.repository.UserRepository;
import com.kimberlysupport.service.SecurityService;
import com.kimberlysupport.util.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private SecurityService securityService;

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout, Principal principal) {

        if (principal != null)
            return "redirect:/";
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "index";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute @Valid User user, BindingResult result, Model model) {

        if (user.getName().length() < 3) {
            result.rejectValue("name", null, "Please enter your name");
        }
        if (user.getGender() == null) {
            result.rejectValue("gender", null, "Please select your gender");
        }
        if (user.getEmail().length() < 3) {
            result.rejectValue("email", null, "Please enter your email id");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            result.rejectValue("email", null, "An account already exists with this email id");
        }
        if (user.getPassword().length() < 6) {
            user.setPassword(null);
            result.rejectValue("password", null, "Please enter atleast 6 characters");
        }
        if (result.hasErrors())
            return "register";
        else {
            String password = user.getPassword();
            user.setPassword(encoder.encode(password));
            user.setRole(Role.USER);
            user = userRepository.save(user);
            securityService.autologin(user.getEmail(), password);
            return "redirect:/chats";
        }
    }
}
