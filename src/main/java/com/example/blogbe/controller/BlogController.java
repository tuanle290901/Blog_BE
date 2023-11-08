package com.example.blogbe.controller;

import com.example.blogbe.model.Blog;
import com.example.blogbe.service.IAccountService;
import com.example.blogbe.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    IBlogService iBlogService;
    @Autowired
    IAccountService iAccountService;

    @PostMapping("/add")
    public ResponseEntity<Blog> addBlog(@RequestBody Blog blog) {
        iBlogService.save(blog);
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Blog> findBlogByID(@PathVariable int id) {
        Blog blog = iBlogService.findById(id);
        if (blog == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Blog>> getAllBlogs() {
        List<Blog> blogs = iBlogService.getAll();
        if (blogs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable int id) {
        iBlogService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
