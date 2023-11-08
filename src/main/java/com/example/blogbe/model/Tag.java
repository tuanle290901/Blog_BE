package com.example.blogbe.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String name;



}
