package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.ProfileService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {
    UserRepository userRepository;
    ProfileService profileService;

    public ProfileController(UserRepository userRepository, ProfileService profileService) {
        this.userRepository = userRepository;
        this.profileService = profileService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping ("/profile")
    public String getProfile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found " + username));
        model.addAttribute("username", username);
        model.addAttribute("role", user.getRole());
        return "profile";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/profile/change-password")
    public String changePassword(@RequestParam String oldPassword, @RequestParam String newPassword) {
        if (profileService.changePassword(oldPassword, newPassword)) {
            return "redirect:/profile?passwordChanged=true";
        }
        return "redirect:/profile?error=password";
    }
}
