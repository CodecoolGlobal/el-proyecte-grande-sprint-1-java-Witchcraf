package com.codecool.PawPrint.repository;

import com.codecool.PawPrint.model.entity.Search;
import com.codecool.PawPrint.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends UserDao, CrudRepository<User, Long>, JpaRepository<User, Long> {

    @Override
    default void add(User user){
        save(user);
    }

//    @Override
//    @Query("insert into user ")
//    default void add(User user, Search search) {
//
//        break;
//    }

    @Override
    default User findById(int id){
        return findById(id);
    }

//    @Override
//    @Query("select u from user u where u.firstname like %?1")
//    User findByName(String name);

    @Override
    default List<User> getAll(){
       return findAll();
    }
}
