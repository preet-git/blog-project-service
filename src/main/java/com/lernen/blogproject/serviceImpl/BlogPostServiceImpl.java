package com.lernen.blogproject.serviceImpl;

import com.lernen.blogproject.dto.BlogPostRequest;
import com.lernen.blogproject.exception.PostNotFoundException;
import com.lernen.blogproject.model.Post;
import com.lernen.blogproject.repository.BlogPostRepository;
import com.lernen.blogproject.service.AuthService;
import com.lernen.blogproject.service.BlogPostService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private AuthService authService;
    @Override
    public void postBlog(BlogPostRequest request) {

        Post postRequest = mapFromBlogPostResquestToPost(request);
        blogPostRepository.save(postRequest);

    }

    @Override
    public List<BlogPostRequest> getAllPost() {
        List<Post> postsList = blogPostRepository.findAll();
        return postsList.stream().map(this::mapFromPostToBlogPostRequest).collect(toList());
    }

    @Override
    public BlogPostRequest getPostById(Long id) {
       Post postModel = blogPostRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Not found: " + id));
       return mapFromPostToBlogPostRequest(postModel);
    }

    private BlogPostRequest mapFromPostToBlogPostRequest(Post post) {
        BlogPostRequest blogPostRequest = new BlogPostRequest();
        blogPostRequest.setId(post.getId());
        blogPostRequest.setTitle(post.getTitle());
        blogPostRequest.setContent(post.getContent());
        blogPostRequest.setUsername(post.getUserName());

        return blogPostRequest;
    }

    private Post mapFromBlogPostResquestToPost(BlogPostRequest blogPostRequest){
        Post postRequest = new Post();

        postRequest.setTitle(blogPostRequest.getTitle());
        postRequest.setContent(blogPostRequest.getContent());
        User username = authService.getCurrentUser().orElseThrow(()->
                new IllegalArgumentException("No user found."));
        postRequest.setUserName(username.getUsername());
        postRequest.setCreatedOn(Instant.now());

        return postRequest;
    }

}
