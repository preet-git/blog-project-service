package com.lernen.blogproject.controller;

import com.lernen.blogproject.dto.BlogPostRequest;
import com.lernen.blogproject.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @RequestMapping(method = RequestMethod.POST,path = "/blogPost")
    public ResponseEntity<String> postBlog(@RequestBody BlogPostRequest blogPostRequest){
        blogPostService.postBlog(blogPostRequest);

        return new ResponseEntity<>("Blog uploaded SuccessFully.", HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET,path="/getAllPosts")
    public ResponseEntity<List<BlogPostRequest>> getAllPosts(){
        return new ResponseEntity<>(blogPostService.getAllPost(),HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET,path = "/getPost{id}")
    public ResponseEntity<BlogPostRequest> getPostOfId(@PathVariable @RequestBody Long id){
        return new ResponseEntity<>(blogPostService.getPostById(id),HttpStatus.OK);
    }
}
