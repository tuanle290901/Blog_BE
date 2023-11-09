package com.example.blogbe.controller;

import com.example.blogbe.model.Account;
import com.example.blogbe.model.Blog;
import com.example.blogbe.service.IAccountService;
import com.example.blogbe.service.IBlogService;
import com.example.blogbe.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

    @Autowired
    IBlogService iBlogService;
    @Autowired
    AccountService accountService;


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
    @GetMapping("/account/{accountId}")
    public List<Blog> getBlogsByAccountId(@PathVariable int accountId) {
        return iBlogService.getBlogsByAccountId(accountId);
    }


    public Account getCurrentAccount() {
        try {
            String email = "";
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.isAuthenticated()) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                email = userDetails.getUsername();
            }
            return accountService.findByEmail(email);

        } catch (Exception e) {
            return null;
        }
    }
    @PostMapping("/add")
    public ResponseEntity<?> addBlog(@RequestBody Blog blog) {
        Account account = getCurrentAccount();
        if (account == null) {
            return new ResponseEntity<>("Vui lòng đăng nhập", HttpStatus.UNAUTHORIZED);
        }
        Blog savedBlog = iBlogService.addBlog(account, blog);
        return new ResponseEntity<>(savedBlog, HttpStatus.OK);
    }

}
