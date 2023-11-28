package com.example.blogbe.service;

import com.example.blogbe.model.Account;
import com.example.blogbe.model.Blog;

import java.util.List;

public interface IBlogService {
    Blog save(Blog blog);

    List<Blog> getAll();

    List<Blog> getAllNoPermissions();

    Blog findById(int id);


    List<Blog> getBlogsByTitle(String title);

    List<Blog> getBlogsByTitleForPermissions(String title);

    void delete(int id);
    List<Blog> getBlogsByAccountId(int accountId);


    List<Blog> getBlogsByAccountIdNoPermission(int accountId);

    Blog addBlog(Account account, Blog blog);


    List<Blog> getBlogsByTagName(String tagName);

    List<Blog> getLatest4Blogs();

    List<Blog> getLatest4BlogsNew();
}
