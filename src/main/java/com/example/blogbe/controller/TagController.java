package com.example.blogbe.controller;

import com.example.blogbe.model.Blog;
import com.example.blogbe.model.Tag;
import com.example.blogbe.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagController {

    @Autowired
    ITagService iTagService;


    @GetMapping("/getall")
    public ResponseEntity<List<Tag>> getAll() {
        List<Tag> tags = iTagService.getAll();
        if (tags.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Tag> findBlogByID(@PathVariable int id) {
        Tag tag = iTagService.findById(id);
        if (tag == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

}
