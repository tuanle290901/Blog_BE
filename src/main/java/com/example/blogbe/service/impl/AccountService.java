package com.example.blogbe.service.impl;


import com.example.blogbe.exeption.EmailExitsException;
import com.example.blogbe.model.Account;
import com.example.blogbe.model.AccountPrinciple;
import com.example.blogbe.model.Role;
import com.example.blogbe.repository.IAccountRepository;
import com.example.blogbe.repository.IRoleRepository;
import com.example.blogbe.request.RegisterRequest;
import com.example.blogbe.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountService implements UserDetailsService, IAccountService {

    @Autowired
    private IAccountRepository iAccountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    IRoleRepository iRoleRepository;

    public Account findAllByEmail(String email) {
        return iAccountRepository.findAllByEmail(email);
    }

    public Optional<Account> findAccountByEmail(String email) {
        return iAccountRepository.findAccountByEmail(email);
    }

    public void add(RegisterRequest registerRequest) throws Exception {

        if (iAccountRepository.existsByEmail(registerRequest.getEmail())) {
            throw new EmailExitsException("Email đã tồn tại");
        }
        Account account = new Account();
        if (registerRequest.getRole() == null) {
            Role role = iRoleRepository.findByName("ROLE_USER");
            account.setRole(role);
        } else {
            account.setRole(registerRequest.getRole());
        }
        account.setName(registerRequest.getName());
        account.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        account.setEmail(registerRequest.getEmail());
        account.setImg(registerRequest.getImg());
        account.setPhoneNumber(registerRequest.getPhone());
        account.setAddress(registerRequest.getAddress());
        iAccountRepository.save(account);
    }

    public Account save(Account account) {
        return iAccountRepository.save(account);
    }


    public UserDetails loadUserByUsername(String email) {
        List<Account> accounts = iAccountRepository.findAll();
        for (Account account : accounts) {
            if (Objects.equals(account.getEmail(), email)) {
                return AccountPrinciple.build(account);
            }
        }
        return null;
    }

    @Override
    public List<Account> getAll() {
        return iAccountRepository.findAll();
    }

    @Override
    public Account findById(int id) {
        return iAccountRepository.findById(id).get();
    }



    @Override
    public Integer getAccountQuantity() {
        return iAccountRepository.countAccountByRoleId(1);
    }

    @Override
    public Integer setAuth(@PathVariable Integer id) {
        return iAccountRepository.setAuth(id);
    }

    @Override
    @Transactional
    public void update(Account account) {
        iAccountRepository.save(account);
    }

    public Account findByEmail(String email) {
        return iAccountRepository.findAccountByEmail(email).get();
    }
}
