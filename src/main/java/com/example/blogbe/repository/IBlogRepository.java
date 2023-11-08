package com.example.blogbe.repository;

import com.example.blogbe.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBlogRepository extends JpaRepository<Blog, Integer> {
}
