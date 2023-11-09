package com.example.blogbe.service;

import com.example.blogbe.model.Account;
import com.example.blogbe.model.Blog;

import java.util.List;

public interface IBlogService {
    Blog save(Blog blog);

    List<Blog> getAll();

    Blog findById(int id);


    void delete(int id);
    List<Blog> getBlogsByAccountId(int accountId);


    Blog addBlog(Account account, Blog blog);



}
