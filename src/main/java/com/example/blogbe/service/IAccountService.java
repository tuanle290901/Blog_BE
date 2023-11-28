package com.example.blogbe.service;



import com.example.blogbe.model.Account;

import java.util.List;

public interface IAccountService {
    List<Account> getAll();

    Account findById(int id);

    Integer getAccountQuantity();

    Integer setAuth(Integer id);
    void update(Account account);

}
