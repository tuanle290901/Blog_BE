package com.example.blogbe.repository;

import com.example.blogbe.model.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface IPermissionsRepository extends JpaRepository<Permissions, Integer> {
}
