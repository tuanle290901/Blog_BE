package com.example.blogbe.service;

import com.example.blogbe.model.Account;

import java.util.List;

public interface IAccountService {
    Account save(Account account);

    List<Account> getAll();
    Account findById(int id);




}
