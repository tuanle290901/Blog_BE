package com.example.blogbe.model.dto;

import com.example.blogbe.model.Blog;
import com.example.blogbe.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDTO {

    private int id;
    private int likes;
    private String title;
    private String content;
    private String avatar;
    private String image;

    private LocalDateTime timeCreate;
    private LocalDateTime timeEdit;
    private String description;
    private Tag tag;

    public BlogDTO(Blog blog) {
        this.id = blog.getId();
        this.likes = blog.getLikes();
        this.title = blog.getTitle();
        this.content = blog.getContent();
        this.avatar = blog.getAvatar();
        this.image = blog.getImage();
        this.description = blog.getDescription();
        this.timeCreate = blog.getTimeCreate();
        this.timeEdit = blog.getTimeEdit();
        this.tag=blog.getTag();
    }
}
