package com.kimberlysupport.service;

import com.kimberlysupport.model.User;
import com.kimberlysupport.repository.UserRepository;
import com.kimberlysupport.security.UserPrincipal;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Log
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Value("${hangon.image.upload.location}")
    public String uploadLocation;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private SecurityService securityService;

    public RedirectAttributes uploadProfilePicture(MultipartFile multipartFile, Principal principal, RedirectAttributes redir) {
        User user = userRepository.findByEmail(principal.getName()).get();
        String filename = user.getEmail().split("@")[0] + "PROFILEPIC" + new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + ".jpg";
        if (saveFile(multipartFile, filename)) {
            user.setProfilePicSrc(filename);
            user = userRepository.save(user);
            redir.addFlashAttribute("msg", "Your profile picture changed successfully");

       } else redir.addFlashAttribute("msg", "Internal server error !");
        return redir;
    }

    public RedirectAttributes uploadWalPicture(MultipartFile multipartFile, Principal principal, RedirectAttributes redir) {
        User user = userRepository.findByEmail(principal.getName()).get();
        String filename = user.getEmail().split("@")[0] + "WALL" + new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + ".jpg";
        if (saveFile(multipartFile, filename)) {
            user.setWallPicSrc(filename);
            user = userRepository.save(user);
            redir.addFlashAttribute("msg", "Your wall picture changed successfully");

        } else redir.addFlashAttribute("msg", "Internal server error !");
        return redir;
    }

    public boolean saveFile(MultipartFile multipartFile, String filename) {
        try {
            File file = new File(uploadLocation);
            if (!file.exists()) {
                if (file.mkdir()) {
                    log.info("Directory is created!");
                } else {
                    log.info("Failed to create directory!");
                }
            }
            log.info("uploading file: " + filename);
            file = new File(uploadLocation + filename);
            multipartFile.transferTo(file);
        } catch (Exception e) {
            log.info("Error occurred while uploading file :" + e.getMessage());
            return false;
        }
        return true;
    }


    public void updateAuthenticationName(User updatedUser) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal user = (UserPrincipal) auth.getPrincipal();
            user.setName(updatedUser.getName());
            user.setName(updatedUser.getNikname());
            user.setProfilePicSrc(user.getProfilePicSrc());
            user.setWallPicSrc(user.getWallPicSrc());
            user.setEmail(updatedUser.getEmail());
        Authentication newAuth = new UsernamePasswordAuthenticationToken(user, auth.getCredentials(), auth.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }


    public UserPrincipal getLoggedInUserPrincipal(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal user = (UserPrincipal) auth.getPrincipal();
        return user;
    }

    public User getLoggedInUser(){
        return userRepository.findByUniqueId(getLoggedInUserPrincipal().getUniqueId()).orElse(null);
    }
}
