package com.social.service;

import com.social.web.backend.model.User;
import com.social.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.HashSet;

@Service
public class FollowService {

    @Autowired
    private UserRepository userRepository;

    public boolean follow(Long followerId, Long followingId) {
        if (followerId == null || followingId == null || followerId.equals(followingId)) {
            return false;
        }

        Optional<User> followerOpt = userRepository.findById(followerId);
        Optional<User> followingOpt = userRepository.findById(followingId);

        if (followerOpt.isPresent() && followingOpt.isPresent()) {
            User follower = followerOpt.get();
            User following = followingOpt.get();

            if (!follower.getFollowing().contains(following)) {
                follower.getFollowing().add(following);
                userRepository.save(follower);
                return true;
            }
        }
        return false;
    }

    public Set<User> getFollowers(Long userId) {
        return userRepository.findById(userId)
                .map(User::getFollowers)
                .orElse(new HashSet<>());
    }

    public Set<User> getFollowing(Long userId) {
        return userRepository.findById(userId)
                .map(User::getFollowing)
                .orElse(new HashSet<>());
    }
}
