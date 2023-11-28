package com.example.blogbe.service.impl;

import com.example.blogbe.model.Tag;
import com.example.blogbe.repository.ITagRepository;
import com.example.blogbe.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TagServiceImpl implements ITagService {
    private final ITagRepository iTagRepository;

    @Autowired
    public TagServiceImpl(ITagRepository iTagRepository) {
        this.iTagRepository = iTagRepository;
    }

    @Override
    public List<Tag> getAll() {
        return iTagRepository.findAll();
    }

    @Override
    public Tag findById(int id) {
        return iTagRepository.findById(id).get();
    }


}




