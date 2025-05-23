package com.social.controller;

import com.social.web.backend.model.User;
import com.social.repositories.UserRepository;
import com.social.dto.UserDTO;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/login")
    public UserDTO login(@RequestBody Map<String, String> body, HttpSession session) {
        String username = body.get("username");
        User user = userRepo.findByUsername(username).orElseGet(() -> {
            // Signup if user doesn't exist
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setFullName(username); // You can customize this if needed
            return userRepo.save(newUser);
        });

        session.setAttribute("userId", user.getId());
        return new UserDTO(user.getId(), user.getUsername(), user.getFullName());
    }
}
