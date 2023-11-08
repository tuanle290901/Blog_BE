package com.example.blogbe.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String username;

    @Column(length = 255)
    private String password;

    @Column(length = 150)
    private String email;

    @Lob
    private String img;
    @Column(length = 255)
    private String address;

    @Column
    private String phoneNumber;

    @Column(length = 255)
    private String fullName;
}
