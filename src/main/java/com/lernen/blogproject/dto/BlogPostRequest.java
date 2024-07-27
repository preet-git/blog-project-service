package com.lernen.blogproject.dto;

import lombok.Data;

@Data
public class BlogPostRequest {
    private long id;

    private String title;

    private String content;

    private String username;
}
