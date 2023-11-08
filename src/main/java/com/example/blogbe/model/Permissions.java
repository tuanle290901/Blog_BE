package com.example.blogbe.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Permissions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 1000)
    private String name;

    @ManyToOne
    private Account account;
}
