package com.social.service;

import com.social.web.backend.model.Post;
import com.social.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post createPost(Post post) {
        post.setTimestamp(LocalDateTime.now());
        return postRepository.save(post);
    }
    public List<Post> getPostsByUserId(Long userId) {
    return postRepository.findByUserId(userId);
}
    
}


