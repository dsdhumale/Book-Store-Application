package com.bridgelabz.BookStoreApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bridgelabz.BookStoreApp.dto.CartDTO;
import com.bridgelabz.BookStoreApp.dto.ResponseDTO;
import com.bridgelabz.BookStoreApp.service.ICartService;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/bookstore/cart")
public class CartController {

    @Autowired
    ICartService cartService;

    /*
     * API for add book to cart
     * http://localhost:8080/bookstore/cart/add
     */
    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addToBook(@RequestBody CartDTO cartDTO,
            @RequestHeader(name = "Authorization") String token) {
        ResponseDTO responseDTO = new ResponseDTO("Inserted new Book", cartService.addBookToCart(cartDTO, token));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    /*
     * API for get all cart items
     * http://localhost:8080/bookstore/cart/getall
     */
    @GetMapping("/getall")
    public ResponseEntity<ResponseDTO> getAll() {
        ResponseDTO responseDTO = new ResponseDTO("Here are all the Cart Items...", cartService.getAll());
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /*
     * API for get perticular cart by id
     * http://localhost:8080/bookstore/cart/get/1
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable int id) {
        ResponseDTO responseDTO = new ResponseDTO("Here are all the Cart Items...", cartService.getById(id));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /*
     * API for get perticular cart by user id
     * http://localhost:8080/bookstore/cart/getbyuser/2
     */
    @GetMapping("/getbyuser")
    public ResponseEntity<ResponseDTO> getByUserId(@RequestHeader(name = "Authorization") String token) {
        ResponseDTO responseDTO = new ResponseDTO("Here are all the Cart Items...", cartService.getByUserId(token));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /*
     * API for remove cart item by id
     * http://localhost:8080/bookstore/cart/remove/1
     */
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<ResponseDTO> removeFromCart(@RequestHeader(name = "Authorization") String token,
            @PathVariable int id) {
        ResponseDTO responseDTO = new ResponseDTO("Cart item removed successfully", cartService.removeById(id, token));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /*
     * API for update quantity of perticular cart by id
     * http://localhost:8080/bookstore/cart/updateQuantity/1
     */
    @PutMapping("/updateQuantity/{id}")
    public ResponseEntity<ResponseDTO> updateQuantity(@RequestHeader(name = "Authorization") String token,
            @RequestBody CartDTO cartDTO, @PathVariable long id) {
        ResponseDTO responseDTO = new ResponseDTO("Cart Successfully Updated",
                cartService.updateQuantity(token, cartDTO, id));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.ACCEPTED);
    }

     /*
     * API for update quantity of perticular cart by id
     * http://localhost:8080/bookstore/cart/addQuantity/1
     */
    @PutMapping("/addQuantity")
    public ResponseEntity<ResponseDTO> addQuantity(@RequestBody CartDTO cartDTO
    ,@RequestHeader(name = "Authorization") String token) {
        ResponseDTO responseDTO = new ResponseDTO("Cart Successfully Updated",
                cartService.addQuantity( token,cartDTO));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.ACCEPTED);
    }

     /*
     * API for update quantity of perticular cart by id
     * http://localhost:8080/bookstore/cart/addQuantity/1
     */
    @PutMapping("/reduceQuantity")
    public ResponseEntity<ResponseDTO> reduceQuantity(@RequestBody CartDTO cartDTO,@RequestHeader(name = "Authorization") String token) {
        ResponseDTO responseDTO = new ResponseDTO("Cart Successfully Updated",
                cartService.reduceQuantity( token, cartDTO));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.ACCEPTED);
    }

}
