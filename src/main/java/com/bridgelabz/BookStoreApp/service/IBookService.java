package com.bridgelabz.BookStoreApp.service;

import java.util.List;

import com.bridgelabz.BookStoreApp.dto.BookDTO;
import com.bridgelabz.BookStoreApp.model.BookModel;

public interface IBookService {

    public BookModel insertBook(String token, BookDTO bookDTO);
    
    public List<BookModel> getAllBooks();

    public BookModel getBookById(long bookId );

    public BookModel updateBookById(String token, long bookId, BookDTO bookDTO);

    public BookModel deleteBookById(String token, long bookId);

    public BookModel updateQuantity(String token, long bookId, int quantity);
    
    public BookModel updatePrice(String token, long bookId, int price);
    
}
