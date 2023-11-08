package com.example.blogbe.service.impl;

import com.example.blogbe.model.Blog;
import com.example.blogbe.repository.IBlogRepository;
import com.example.blogbe.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements IBlogService {
    private final IBlogRepository iBlogRepository;

    @Autowired
    public BlogServiceImpl(IBlogRepository iBlogRepository) {
        this.iBlogRepository = iBlogRepository;
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
    public void delete(int id){

iBlogRepository.deleteById(id);

    }
}
