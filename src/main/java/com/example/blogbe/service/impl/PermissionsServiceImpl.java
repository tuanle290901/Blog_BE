package com.example.blogbe.service.impl;

import com.example.blogbe.model.Permissions;
import com.example.blogbe.repository.IPermissionsRepository;
import com.example.blogbe.service.IPermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PermissionsServiceImpl implements IPermissionsService {
    private final IPermissionsRepository iPermissionsRepository;

    @Autowired
    public PermissionsServiceImpl(IPermissionsRepository iPermissionsRepository) {
        this.iPermissionsRepository = iPermissionsRepository;
    }

    @Override
    public List<Permissions> getAll() {
        return iPermissionsRepository.findAll();
    }

    @Override
    public Permissions findById(int id) {
        return iPermissionsRepository.findById(id).get();
    }


}




