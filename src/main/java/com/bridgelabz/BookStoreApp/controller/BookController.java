package com.bridgelabz.BookStoreApp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.BookStoreApp.dto.BookDTO;
import com.bridgelabz.BookStoreApp.dto.ResponseDTO;
import com.bridgelabz.BookStoreApp.service.IBookService;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/bookstore/book")
public class BookController {

    @Autowired
    IBookService bookService;


    /* API for Insert book details to database
     * http://localhost:8080/bookstore/book/insert
     */
    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> insertBook(@RequestHeader(name = "Authorization") String token, @RequestBody BookDTO bookDTO) {
        ResponseDTO responseDTO = new ResponseDTO("Inserted new Book", bookService.insertBook(token, bookDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    /**
     * API for retrieve all book details from database
     * http://localhost:8080/bookstore/book/get
     */
    @GetMapping("/get")
    public ResponseEntity<ResponseDTO> getAllBooks() {
        ResponseDTO responseDTO = new ResponseDTO("Books fetched successfully", bookService.getAllBooks());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * API for retrieve book details by id from database
     * http://localhost:8080/bookstore/book/get/1
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getBookById(@PathVariable long id) {
        ResponseDTO responseDTO = new ResponseDTO("Books fetched successfully", bookService.getBookById(id));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

       /**
     * API for Update book details by id to database
     * http://localhost:8080/bookstore/book/update/1
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateBookById(@RequestHeader(name = "Authorization") String token, @PathVariable long id, @RequestBody BookDTO bookDTO) {
        ResponseDTO responseDTO = new ResponseDTO("Book Updated", bookService.updateBookById(token, id, bookDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

     /**
     * API for Delete book details by name to database
     * http://localhost:8080/bookstore/book/delete/1
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteBookById(@RequestHeader(name = "Authorization") String token, @PathVariable long id) {
        ResponseDTO responseDTO = new ResponseDTO("Book successfully deleted", bookService.deleteBookById(token, id));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * API for Update book quantity 
     * http://localhost:8080/bookstore/book/updatequantity/2?quantity=12
     */
    @PutMapping("/updatequantity/{id}")
    public ResponseEntity<ResponseDTO> updateQuantity(@RequestHeader(name = "Authorization") String token,@PathVariable long id, @RequestParam int quantity) {
        ResponseDTO responseDTO = new ResponseDTO("Book quantity updated", bookService.updateQuantity(token, id, quantity));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * API for Update book price
     * http://localhost:8080/bookstore/book/updateprice/2?price=1000
     */
    @PutMapping("/updateprice/{id}")
    public ResponseEntity<ResponseDTO> updatePrice(@RequestHeader(name = "Authorization") String token,@PathVariable long id, @RequestParam int price) {
        ResponseDTO responseDTO = new ResponseDTO("Book price updated", bookService.updatePrice(token, id, price));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
