package com.example.blogbe.repository;

import com.example.blogbe.model.Blog;
import com.example.blogbe.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITagRepository extends JpaRepository<Tag,Integer> {


}
