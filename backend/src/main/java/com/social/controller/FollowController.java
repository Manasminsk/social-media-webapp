
package com.social.controller;

import com.social.web.backend.model.User;
import com.social.service.FollowService;
import com.social.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/follow")
@CrossOrigin(origins = "*")
public class FollowController {

    @Autowired
    private FollowService followService;

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public String follow(@RequestParam Long fromId, @RequestParam Long toId, @RequestParam String fromUsername) {
        boolean success = followService.follow(fromId, toId);
        if (success) {
            notificationService.createFollowNotification(toId, fromUsername);
            return "Followed successfully.";
        }
        return "Follow failed.";
    }

    @GetMapping("/followers/{userId}")
    public Set<User> getFollowers(@PathVariable Long userId) {
        return followService.getFollowers(userId);
    }

    @GetMapping("/following/{userId}")
    public Set<User> getFollowing(@PathVariable Long userId) {
        return followService.getFollowing(userId);
    }
}
