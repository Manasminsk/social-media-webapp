package com.social.controller;

import com.social.web.backend.model.Post;
import com.social.web.backend.model.User;
import com.social.repositories.UserRepository;
import com.social.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @GetMapping("/feed/{userId}")
    public List<Post> getFeed(@PathVariable Long userId) {
        return userRepository.findById(userId).map(user -> {
            List<User> following = new ArrayList<>(user.getFollowing());
            List<Post> feed = new ArrayList<>();
            for (User u : following) {
                feed.addAll(postService.getPostsByUserId(u.getId()));
            }
            feed.sort((a, b) -> b.getTimestamp().compareTo(a.getTimestamp()));
            return feed;
        }).orElse(Collections.emptyList());
    }
}
