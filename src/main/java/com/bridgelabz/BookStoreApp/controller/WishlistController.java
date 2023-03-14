package com.bridgelabz.BookStoreApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.BookStoreApp.dto.ResponseDTO;
import com.bridgelabz.BookStoreApp.service.IWishlistService;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/bookstore/wishlist")
public class WishlistController {

    @Autowired
    IWishlistService service;

     /*
     * API for add book to wishlist
     * http://localhost:8080/bookstore/wishlist/add
     */
    @PostMapping("/add/{id}")
    public ResponseEntity<ResponseDTO> addToBook(@PathVariable long id,
            @RequestHeader(name = "Authorization") String token) {
        ResponseDTO responseDTO = new ResponseDTO("Book added to wishlist", service.addBookToWishlist(id, token));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

     /*
     * API for get perticular wishlist by user id
     * http://localhost:8080/bookstore/wishlist/getbyuser
     */
    @GetMapping("/getbyuser")
    public ResponseEntity<ResponseDTO> getByUserId(@RequestHeader(name = "Authorization") String token) {
        ResponseDTO responseDTO = new ResponseDTO("Here are all the Cart Items...", service.getByUserId(token));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

       /*
     * API for remove wishlist item by id
     * http://localhost:8080/bookstore/wishlist/remove/1
     */
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<ResponseDTO> removeFromCart(@PathVariable long id,@RequestHeader(name = "Authorization") String token
            ) {
        ResponseDTO responseDTO = new ResponseDTO("Cart item removed successfully", service.removeById(id, token));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }


    
}
