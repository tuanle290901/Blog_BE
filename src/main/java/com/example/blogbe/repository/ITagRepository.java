package com.example.blogbe.repository;

import com.example.blogbe.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITagRepository extends JpaRepository<Tag,Integer> {


}
