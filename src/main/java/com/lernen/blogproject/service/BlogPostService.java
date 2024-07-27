package com.lernen.blogproject.service;

import com.lernen.blogproject.dto.BlogPostRequest;
import com.lernen.blogproject.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BlogPostService {
    public void postBlog(BlogPostRequest request);

     public List<BlogPostRequest> getAllPost();

    public BlogPostRequest getPostById(Long id);
}
