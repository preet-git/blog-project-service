package com.lernen.blogproject.repository;

import com.lernen.blogproject.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<Post,Long> {
}
