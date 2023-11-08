package com.example.blogbe.repository;

import com.example.blogbe.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account,Integer> {

}
