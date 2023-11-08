package com.example.blogbe.model.dto;


import com.example.blogbe.model.Role;
import org.springframework.context.support.BeanDefinitionDsl;

public class AccountDTO {
    private Long id;
    private String name;
    private Role roles;

    public AccountDTO() {
    }

    public AccountDTO(Long id, String name, Role roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }
}
