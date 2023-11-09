package com.example.blogbe.service.impl;

import com.example.blogbe.model.Account;
import com.example.blogbe.model.Blog;
import com.example.blogbe.repository.IAccountRepository;
import com.example.blogbe.repository.IBlogRepository;
import com.example.blogbe.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BlogServiceImpl implements IBlogService {
    private final IBlogRepository iBlogRepository;
    private final IAccountRepository iAccountRepository;

    @Autowired
    public BlogServiceImpl(IBlogRepository iBlogRepository, IAccountRepository iAccountRepository) {
        this.iBlogRepository = iBlogRepository;
        this.iAccountRepository = iAccountRepository;
    }


    @Override
    public Blog save(Blog blog) {
        return iBlogRepository.save(blog);
    }

    @Override
    public List<Blog> getAll() {
        return iBlogRepository.findAll();
    }

    @Override
    public Blog findById(int id) {
        return iBlogRepository.findById(id).orElse(null);

    }

    @Override
    public void delete(int id) {

        iBlogRepository.deleteById(id);

    }

    @Override
    public List<Blog> getBlogsByAccountId(int accountId) {
        return iBlogRepository.getBlogsByAccountId(accountId);
    }

    @Override
    public Blog addBlog(Account account, Blog blog) {
        blog.setTimeCreate(LocalDate.now());
        blog.setAccount(account);
        iBlogRepository.save(blog);
        return blog;
    }

}




