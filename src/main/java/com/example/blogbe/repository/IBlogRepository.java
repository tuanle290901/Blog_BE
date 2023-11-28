package com.example.blogbe.repository;

import com.example.blogbe.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IBlogRepository extends JpaRepository<Blog, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM blog WHERE account_id = :account_id AND permissions_id = :permissions_id")
    List<Blog> getBlogsByAccountIdAndPermissionsId(@Param("account_id") int accountId, @Param("permissions_id") int permissionsId);

    @Query("SELECT b FROM Blog b JOIN b.tag t WHERE t.name = :tagName AND b.permissions.id = :permissionsId")
    List<Blog> getBlogsByTagNameAndPermissionsId(@Param("tagName") String tagName, @Param("permissionsId") int permissionsId);

    @Query("SELECT b FROM Blog b WHERE b.title = :title AND b.permissions.id = :permissionsId")
    List<Blog> getBlogsByTitleAndPermissionsId(@Param("title") String title, @Param("permissionsId") int permissionsId);

    @Query("SELECT b FROM Blog b WHERE b.permissions.id = :permissionsId ORDER BY b.timeCreate DESC")
    List<Blog> findTop4ByPermissionsOrderByTimeCreateDesc(@Param("permissionsId") int permissionsId);

    @Query("SELECT b FROM Blog b WHERE b.permissions.id = :permissionsId ORDER BY b.timeCreate ASC")
    List<Blog> findTop4ByPermissionsOrderByTimeCreateAsc(@Param("permissionsId") int permissionsId);

    @Query("SELECT b FROM Blog b JOIN b.tag t WHERE t.name = :tagName AND b.permissions.id = :permissionsId")
    List<Blog> getBlogsByTagNameAndPermissions(@Param("tagName") String tagName, @Param("permissionsId") int permissionsId);

    @Query("SELECT b FROM Blog b WHERE b.permissions.id = :permissionsId")
    List<Blog> findAllByPermissionsId(@Param("permissionsId") int permissionsId);

    @Query("SELECT b FROM Blog b WHERE b.id = :id AND b.permissions.id = :permissionsId")
    Optional<Blog> findByIdAndPermissionsId(@Param("id") int id, @Param("permissionsId") int permissionsId);
    @Query("SELECT b FROM Blog b WHERE b.account.id = :accountId")
    List<Blog> findByAccountId(@Param("accountId") int accountId);
    @Query("SELECT b FROM Blog b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%')) AND b.permissions.id = :permissionsId")
    List<Blog> findByTitleContainingAndPermissionsId(@Param("title") String title, @Param("permissionsId") int permissionsId);

}
