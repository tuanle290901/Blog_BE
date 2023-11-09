package com.example.blogbe.controller;


import com.example.blogbe.exeption.EmailExitsException;
import com.example.blogbe.jwt.service.service.JwtResponse;
import com.example.blogbe.jwt.service.service.JwtService;
import com.example.blogbe.model.Account;
import com.example.blogbe.model.Blog;
import com.example.blogbe.request.LoginRequest;
import com.example.blogbe.request.RegisterRequest;
import com.example.blogbe.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
         Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Account userInfo = accountService.findByEmail(loginRequest.getEmail());
        return ResponseEntity.ok(new JwtResponse(userInfo.getId(), jwt,
                userInfo.getEmail(), userInfo.getName(), userInfo.getImg(), userDetails.getAuthorities(), userInfo.isAuth()));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            accountService.add(registerRequest);
            return new ResponseEntity<>(accountService.findAccountByEmail(registerRequest.getEmail()), HttpStatus.OK);
        } catch (EmailExitsException emailExitsException) {
            return new ResponseEntity<>(emailExitsException.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("Lỗi hệ thống, chi tiết: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Account> findAccountByID(@PathVariable int id) {
        Account account = accountService.findById(id);

        if (account == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(account);
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ResponseEntity<?> check(@RequestBody Account account) {
        boolean check = true;
        Account accountCheck = null;
        if (accountService.findAccountByEmail(account.getEmail()).isPresent()) {
            accountCheck = accountService.findAccountByEmail(account.getEmail()).get();
            check = false;
        }
        if (!check) {
            return new ResponseEntity<>(accountCheck, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(account, HttpStatus.BAD_REQUEST);
        }
    }
}
