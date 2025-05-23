package com.social.controller;

import com.social.repositories.UserRepository;
import com.social.service.FollowService;
import com.social.web.backend.model.User;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private FollowService followService;

    // DTO to return basic user info
    public static record UserDTO(Long id, String username, String fullName) {}

    // 1) Current user info
    @GetMapping("/me")
    public UserDTO me(HttpSession session) {
        Long me = (Long) session.getAttribute("userId");
        User u = userRepo.findById(me).orElseThrow();
        return new UserDTO(u.getId(), u.getUsername(), u.getFullName());
    }

    // 2) Followers list
    @GetMapping("/me/followers")
    public List<UserDTO> followers(HttpSession session) {
        Long me = (Long) session.getAttribute("userId");
        return followService.getFollowers(me).stream()
                .map(u -> new UserDTO(u.getId(), u.getUsername(), u.getFullName()))
                .collect(Collectors.toList());
    }

    // 2) Following list
    @GetMapping("/me/following")
    public List<UserDTO> following(HttpSession s) {
        Long userId = (Long) s.getAttribute("userId"); // ✅ Define userId
        return new ArrayList<>(followService.getFollowing(userId))
        .stream().map(u -> new UserDTO(u.getId(), u.getUsername(), u.getFullName()))
        .toList();
    }


    // 3) Suggestions (everyone else not followed yet)
    @GetMapping("/suggestions")
public List<UserDTO> suggestions(HttpSession session) {
    Long me = (Long) session.getAttribute("userId"); // ✅ 'me' is defined here
    List<User> all = userRepo.findAll();
    List<User> following = new ArrayList<>(followService.getFollowing(me)); // ✅ use 'me' instead of 'userId'

    return all.stream()
            .filter(u -> !u.getId().equals(me)) // not self
            .filter(u -> !following.contains(u)) // not already following
            .map(u -> new UserDTO(u.getId(), u.getUsername(), u.getFullName()))
            .toList();
}

}