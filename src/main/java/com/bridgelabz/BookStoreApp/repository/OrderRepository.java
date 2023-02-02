package com.bridgelabz.BookStoreApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.BookStoreApp.model.OrderModel;

public interface OrderRepository extends JpaRepository<OrderModel, Long>{

    @Query(value = "select * from bookstore.yourorder where user_id=:userId",nativeQuery = true)
    List<OrderModel> findByUserId(long userId);
    
}
