package com.example.blogbe.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class BlogImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    private String imageUrl;

    @ManyToOne
    private Blog blog;
}
