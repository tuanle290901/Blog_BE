package com.example.blogbe.service.impl;

import com.example.blogbe.model.Account;
import com.example.blogbe.repository.IAccountRepository;
import com.example.blogbe.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements IAccountService {
    private final IAccountRepository iAccountRepository;

    @Autowired
    public AccountServiceImpl(IAccountRepository iAccountRepository) {
        this.iAccountRepository = iAccountRepository;
    }

    @Override
    public Account save(Account account) {
        return iAccountRepository.save(account);
    }

    @Override
    public List<Account> getAll() {
        return iAccountRepository.findAll();
    }

    @Override
    public Account findById(int id) {
        return iAccountRepository.findById(id).orElse(null);

    }


}
