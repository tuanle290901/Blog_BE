package com.example.blogbe.service.impl;

import com.example.blogbe.model.Account;
import com.example.blogbe.model.Blog;
import com.example.blogbe.repository.IAccountRepository;
import com.example.blogbe.repository.IBlogRepository;
import com.example.blogbe.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        return iBlogRepository.findAllByPermissionsId(1);
    }
    @Override
    public List<Blog> getAllNoPermissions() {
        return iBlogRepository.findAll();
    }
    @Override
    public Blog findById(int id) {
        return iBlogRepository.findByIdAndPermissionsId(id, 1).orElse(null);
    }

    @Override
    public List<Blog> getBlogsByTitle(String title) {
        return iBlogRepository.getBlogsByTitleAndPermissionsId(title, 1);
    }
    @Override
    public List<Blog> getBlogsByTitleForPermissions(String title) {
        return iBlogRepository.findByTitleContainingAndPermissionsId(title, 1);
    }

    @Override
    public void delete(int id) {
        iBlogRepository.deleteById(id);
    }

    @Override
    public List<Blog> getBlogsByAccountId(int accountId) {
        return iBlogRepository.getBlogsByAccountIdAndPermissionsId(accountId, 1);
    }

    @Override
    public List<Blog> getBlogsByAccountIdNoPermission(int accountId) {
        return iBlogRepository.findByAccountId(accountId);
    }

    @Override
    public Blog addBlog(Account account, Blog blog) {
        blog.setTimeCreate(LocalDateTime.now());
        blog.setAccount(account);
        iBlogRepository.save(blog);
        return blog;
    }

    @Override
    public List<Blog> getBlogsByTagName(String tagName) {
        return iBlogRepository.getBlogsByTagNameAndPermissionsId(tagName, 1);
    }

    @Override
    public List<Blog> getLatest4Blogs() {
        return iBlogRepository.findTop4ByPermissionsOrderByTimeCreateDesc(1);
    }

    @Override
    public List<Blog> getLatest4BlogsNew() {
        return iBlogRepository.findTop4ByPermissionsOrderByTimeCreateAsc(1);
    }
}
