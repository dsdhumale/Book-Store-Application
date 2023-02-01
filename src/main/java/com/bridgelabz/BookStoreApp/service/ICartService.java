package com.bridgelabz.BookStoreApp.service;

import java.util.List;

import com.bridgelabz.BookStoreApp.dto.CartDTO;
import com.bridgelabz.BookStoreApp.model.CartModel;

public interface ICartService {

    public CartModel addBookToCart(CartDTO cartDTO,String token);

    public List<CartModel> getAll();

    public CartModel getById(long id);

    public CartModel removeById(long id, String token);

    public CartModel updateQuantity(String token, CartDTO cartDTO, long id);

    public CartModel getByUserId(long id);

}
