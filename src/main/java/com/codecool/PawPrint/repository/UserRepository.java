package com.codecool.PawPrint.repository;

import com.codecool.PawPrint.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findById(int id);
    List<User> findAll();
    User findUserByUsername(String name);
    User findUserByEmail(String email);
    User findUserByFullName(String fullname);
    boolean existsByEmail(String email);
    boolean existsByFullName(String fullName);

    @Modifying
    @Query(
            "update User u set u.username = ?1, u.fullName = ?2 where u.email = ?3")
    void updateUserInfoByEmail(@Param(value = "username") String username,@Param(value = "fullname") String fullname, @Param(value = "email") String email);
}
