package com.example.blogbe.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String title;

    private Integer Likes;

    @Column(length = 10000000)
    private String content;
    @Lob
    private String avatar;

    @Column(length = 1000)
    private String description;

    private LocalDateTime timeCreate;

    private LocalDateTime timeEdit;

    @Lob
    private String image;

    @ManyToOne
    private Account account;

    @ManyToOne
    private Tag tag;

    @ManyToOne
    private Comment comment;
@ManyToOne
    private Permissions permissions;

}
