package com.example.blogbe.controller;

import com.example.blogbe.model.Blog;
import com.example.blogbe.model.Permissions;
import com.example.blogbe.model.Tag;
import com.example.blogbe.service.IPermissionsService;
import com.example.blogbe.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionsController {

    @Autowired
    IPermissionsService iPermissionsService;


    @GetMapping("/getall")
    public ResponseEntity<List<Permissions>> getAll() {
        List<Permissions> permissions = iPermissionsService.getAll();
        if (permissions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(permissions, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Permissions> findBlogByID(@PathVariable int id) {
        Permissions permissions = iPermissionsService.findById(id);
        if (permissions == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(permissions, HttpStatus.OK);
    }

}
