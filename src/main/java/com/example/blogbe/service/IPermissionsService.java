package com.example.blogbe.service;

import com.example.blogbe.model.Permissions;

import java.util.List;

public interface IPermissionsService {
    List<Permissions> getAll();
    Permissions findById(int id);
}
