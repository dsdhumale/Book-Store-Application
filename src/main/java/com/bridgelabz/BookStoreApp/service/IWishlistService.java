package com.bridgelabz.BookStoreApp.service;

import java.util.List;

import com.bridgelabz.BookStoreApp.model.WishlistModel;

public interface IWishlistService {

    public WishlistModel addBookToWishlist(long bookId,String token);

    public List<WishlistModel> getByUserId(String token);

    public WishlistModel removeById(long bookId, String token);
    
}
