package com.example.blogbe.repository;

import com.example.blogbe.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBlogRepository extends JpaRepository<Blog, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM blog WHERE account_id = :account_id")
    List<Blog> getBlogsByAccountId(@Param("account_id") int accountId);

}
