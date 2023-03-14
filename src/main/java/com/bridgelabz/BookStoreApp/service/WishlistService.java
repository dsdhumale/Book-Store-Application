package com.bridgelabz.BookStoreApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.BookStoreApp.exception.BookStoreException;
import com.bridgelabz.BookStoreApp.model.BookModel;
import com.bridgelabz.BookStoreApp.model.UserModel;
import com.bridgelabz.BookStoreApp.model.WishlistModel;
import com.bridgelabz.BookStoreApp.repository.BookRepository;
import com.bridgelabz.BookStoreApp.repository.UserRepository;
import com.bridgelabz.BookStoreApp.repository.WishlistRepository;
import com.bridgelabz.BookStoreApp.util.TokenUtil;

@Service
public class WishlistService implements IWishlistService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    BookRepository bookRepo;

    @Autowired
    WishlistRepository wishRepo;

    @Override
    public WishlistModel addBookToWishlist(long bookId, String token) {
        long userId = tokenUtil.decodeToken(token);
        Optional<UserModel> userData = userRepo.findById(userId);
        Optional<BookModel> bookData = bookRepo.findById(bookId);
        if (userData.isPresent() && bookData.isPresent()) {
            WishlistModel wishlist = new WishlistModel(userData.get(), bookData.get());
            return wishRepo.save(wishlist);
        } else {
            throw (new BookStoreException("Record not Found"));
        }
    }

    @Override
    public List<WishlistModel> getByUserId(String token) {
        long userId = tokenUtil.decodeToken(token);
        List<WishlistModel> cart = wishRepo.findByUserId(userId);
        if (cart != null) {
            return cart;
        } else {
            throw new BookStoreException("For given user id " + userId + " data not found ");
        }
    }

    @Override
    public WishlistModel removeById(long id, String token) {
        long userId = tokenUtil.decodeToken(token);
        Optional<UserModel> userData = userRepo.findById(userId);
        Optional<WishlistModel> wishData = wishRepo.findById(id);
        if (userData.isPresent() && wishData.isPresent()) {
            wishRepo.deleteById(id);
            return wishData.get();

        } else {
            throw (new BookStoreException("Record not Found"));
        }
    }

}
