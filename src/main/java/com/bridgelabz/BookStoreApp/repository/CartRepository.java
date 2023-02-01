package com.bridgelabz.BookStoreApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.BookStoreApp.model.CartModel;

@Repository
public interface CartRepository extends JpaRepository<CartModel, Long>{
    @Query(value = "select * from bookstore.cart where user_id=:userId",nativeQuery = true)
    CartModel findByUserId(long userId);

}
