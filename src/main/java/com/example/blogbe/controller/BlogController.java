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
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

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
    @GetMapping("/getallBlog")
    public ResponseEntity<List<Blog>> getAllBlogsNoPermission() {
        List<Blog> blogs = iBlogService.getAllNoPermissions();
        if (blogs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }
    @GetMapping("/getNewBlog")
    public ResponseEntity<List<Blog>> getNewBlog() {
        List<Blog> blogs = iBlogService.getLatest4Blogs();
        if (blogs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @GetMapping("/getBlogNew")
    public ResponseEntity<List<Blog>> getNew5Blog() {
        List<Blog> blogs = iBlogService.getLatest4BlogsNew();
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
    public List<Blog> getBlogsByAccountIdNoPermission(@PathVariable int accountId) {
        return iBlogService.getBlogsByAccountIdNoPermission(accountId);
    }

    @GetMapping("/myblog/{accountId}")
    public List<Blog> getBlogsByAccountId(@PathVariable int accountId) {
        return iBlogService.getBlogsByAccountId(accountId);
    }

    @GetMapping("/tags/{tagName}")
    public List<Blog> getBlogsByTagName(@PathVariable String tagName) {
        return iBlogService.getBlogsByTagName(tagName);
    }

    @GetMapping("/title/{title}")
    public List<Blog> getBlogsByTitle(@PathVariable String title) {
        return iBlogService.getBlogsByTitleForPermissions(title);
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

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateBlog(@PathVariable int id, @RequestBody Blog updatedBlog) {
        Account account = getCurrentAccount();
        if (account == null) {
            return new ResponseEntity<>("Vui lòng đăng nhập", HttpStatus.UNAUTHORIZED);
        }

        Blog existingBlog = iBlogService.findById(id);
        if (existingBlog == null) {
            return new ResponseEntity<>("Không tìm thấy bài viết", HttpStatus.NOT_FOUND);
        }

        if (existingBlog.getAccount().getId() != account.getId()) {
            return new ResponseEntity<>("Bạn không có quyền chỉnh sửa bài viết này", HttpStatus.FORBIDDEN);
        }

        existingBlog.setTitle(updatedBlog.getTitle());
        existingBlog.setDescription(updatedBlog.getDescription());
        existingBlog.setContent(updatedBlog.getContent());
        existingBlog.setTag(updatedBlog.getTag());
        existingBlog.setPermissions(updatedBlog.getPermissions());

        // Add additional update logic as needed

        Blog updated = iBlogService.save(existingBlog);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
    @PostMapping("/{id}/like")
    public ResponseEntity<Void> likeBlog(@PathVariable Integer id) {
        try {
            // Logic to handle liking a blog post
            Blog blog = iBlogService.findById(id);
            if (blog != null) {
                blog.setLikes(blog.getLikes() + 1);
                iBlogService.save(blog);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
