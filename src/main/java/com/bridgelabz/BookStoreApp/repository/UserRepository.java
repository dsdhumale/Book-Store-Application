package com.bridgelabz.BookStoreApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.BookStoreApp.model.UserModel;


public interface UserRepository extends JpaRepository<UserModel, Long>{
    @Query(value="select * from user_details u where u.email= :email",nativeQuery=true)
    Optional<UserModel> findByEmailId(String email);
}
