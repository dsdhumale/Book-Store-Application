package com.bridgelabz.BookStoreApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.BookStoreApp.dto.CartDTO;
import com.bridgelabz.BookStoreApp.exception.BookStoreException;
import com.bridgelabz.BookStoreApp.model.BookModel;
import com.bridgelabz.BookStoreApp.model.CartModel;
import com.bridgelabz.BookStoreApp.model.UserModel;
import com.bridgelabz.BookStoreApp.repository.*;
import com.bridgelabz.BookStoreApp.util.TokenUtil;

@Service
public class CartService implements ICartService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    BookRepository bookRepo;

    @Autowired
    CartRepository cartRepo;

    @Override
    public CartModel addBookToCart(CartDTO cartDTO, String token) {
        long userId = tokenUtil.decodeToken(token);
        Optional<UserModel> userData = userRepo.findById(userId);
        Optional<BookModel> bookData = bookRepo.findById(cartDTO.getBookId());
        if (userData.isPresent() && bookData.isPresent()) {
            if (bookData.get().getQuantity() >= cartDTO.getQuantity() && cartDTO.getQuantity() > 0) {
                double totalPrice = cartDTO.getQuantity() * bookData.get().getPrice();
                CartModel cartDetails = new CartModel(userData.get(), bookData.get(), cartDTO.getQuantity(),
                        totalPrice);
                return cartRepo.save(cartDetails);
            } else {
                throw (new BookStoreException("Book Out Of Stock"));
            }
        } else {
            throw (new BookStoreException("Record not Found"));
        }
    }

    @Override
    public List<CartModel> getAll() {
        List<CartModel> cartDetailsModelList = cartRepo.findAll();
        return cartDetailsModelList;
    }

    @Override
    public CartModel getById(long id) {
        return cartRepo.findById(id)
                .orElseThrow(() -> new BookStoreException("Book  with id " + id + " does not exist in database..!"));
    }

    @Override
    public CartModel removeById(long id, String token) {
        long userId = tokenUtil.decodeToken(token);
        UserModel userRegistrationModule = userRepo.findById(userId).get();
        Optional<CartModel> cart = cartRepo.findById(id);
        if (cart.isPresent() && userRegistrationModule != null) {
            cartRepo.deleteById(id);
            return cart.get();
        }
        throw (new BookStoreException("Record not Found"));
    }

    @Override
    public CartModel updateQuantity(String token, CartDTO cartDTO, long id) throws BookStoreException {
        long userId = tokenUtil.decodeToken(token);
        UserModel user = userRepo.findById(userId).get();
        if (user != null) {
            CartModel cart = cartRepo.findById(id).orElseThrow(() -> new BookStoreException("Cart not found"));
            cart.setQuantity(cartDTO.getQuantity());
            double totalPrice = cartDTO.getQuantity() * cart.bookModel.getPrice();
            cart.setTotalPrice(totalPrice);
            return cartRepo.save(cart);

        } else {
            throw (new BookStoreException("User not Found"));
        }
    }

    @Override
    public List<CartModel> getByUserId(String token) {
        long userId = tokenUtil.decodeToken(token);
        List<CartModel> cart = cartRepo.findByUserId(userId);
        if (cart != null) {
            return cart;
        } else {
            throw new BookStoreException("For given user id " + userId + " data not found ");
        }
    }

    @Override
    public CartModel addQuantity(String token, CartDTO cartDTO) {
        long userId = tokenUtil.decodeToken(token);
        UserModel user = userRepo.findById(userId).get();
        if (user != null) {
            CartModel cart = cartRepo.findById(cartDTO.cartId).orElseThrow(() -> new BookStoreException("Cart not found"));
            cart.setQuantity(cart.quantity + 1);
            double totalPrice = cart.getQuantity() * cart.bookModel.getPrice();
            cart.setTotalPrice(totalPrice);
            return cartRepo.save(cart);

        } else {
            throw (new BookStoreException("User not Found"));
        }
    }

    @Override
    public CartModel reduceQuantity(String token, CartDTO cartDTO) {
        long userId = tokenUtil.decodeToken(token);
        UserModel user = userRepo.findById(userId).get();
        if (user != null) {
            CartModel cart = cartRepo.findById(cartDTO.cartId).orElseThrow(() -> new BookStoreException("Cart not found"));
            if (cart.quantity > 1) {
                cart.setQuantity(cart.quantity - 1);
                double totalPrice = cart.getQuantity() * cart.bookModel.getPrice();
                cart.setTotalPrice(totalPrice);
                return cartRepo.save(cart);
            } else {
                cart.setQuantity(cart.quantity);
                double totalPrice = cart.getQuantity() * cart.bookModel.getPrice();
                cart.setTotalPrice(totalPrice);
                return cartRepo.save(cart);

            }

        } else {
            throw (new BookStoreException("User not Found"));
        }
    }

}
