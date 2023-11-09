package com.example.blogbe.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String title;

    @Column(length = 1000)
    private String content;

    @Lob
    private String avatar;
    @Lob
    private String image;
    @Lob
    private String image2;
    @Lob
    private String image3;

    @Column(length = 1000)
    private String description;

    private LocalDate timeCreate;

    private LocalDate timeEdit;

    @ManyToOne
    private Account account;

    @ManyToOne
    private Tag tag;
}
