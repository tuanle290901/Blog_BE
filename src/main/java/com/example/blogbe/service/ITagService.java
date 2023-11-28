package com.example.blogbe.service;

import com.example.blogbe.model.Blog;
import com.example.blogbe.model.Tag;

import java.util.List;

public interface ITagService {
    List<Tag> getAll();
    Tag findById(int id);


}
