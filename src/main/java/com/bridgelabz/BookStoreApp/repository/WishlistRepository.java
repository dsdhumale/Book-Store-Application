package com.bridgelabz.BookStoreApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.BookStoreApp.model.WishlistModel;

public interface WishlistRepository extends JpaRepository<WishlistModel, Long>{
    @Query(value = "select * from bookstore.wishlist where user_id=:userId",nativeQuery = true)
    List<WishlistModel> findByUserId(long userId);
}
