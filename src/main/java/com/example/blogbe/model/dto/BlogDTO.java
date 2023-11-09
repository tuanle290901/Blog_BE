package com.example.blogbe.model.dto;

import com.example.blogbe.model.Blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDTO {

    private int id;
    private String title;
    private String content;
    private String avatar;
    private String image;
    private String image2;
    private String image3;
    private LocalDate timeCreate;
    private LocalDate timeEdit;
    private String description;


    public BlogDTO(Blog blog) {
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.content = blog.getContent();
        this.avatar = blog.getAvatar();
        this.image = blog.getImage();
        this.image2 = blog.getImage2();
        this.image3 = blog.getImage3();
        this.description = blog.getDescription();
        this.timeCreate = blog.getTimeCreate();
        this.timeEdit = blog.getTimeEdit();
    }

}
