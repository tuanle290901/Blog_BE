package com.example.blogbe.request;


import com.example.blogbe.model.Role;
import lombok.Data;

@Data
public class RegisterRequest {
//    @NotNull
//    @Min(2)
//    @Max(150)
    private String name;
//    @NotNull
//    @Email
    private String email;
//    @NotNull
//    @Pattern()
    private String password;
    private String img;
//    @NotNull
//    @Pattern()
    private String phone;
    private Role role;
}
