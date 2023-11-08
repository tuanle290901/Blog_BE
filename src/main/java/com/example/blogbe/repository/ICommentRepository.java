package com.example.blogbe.repository;

import com.example.blogbe.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepository extends JpaRepository<Comment,Integer> {
}
