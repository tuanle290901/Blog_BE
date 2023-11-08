package com.example.blogbe.repository;


import com.example.blogbe.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(String name);
}
