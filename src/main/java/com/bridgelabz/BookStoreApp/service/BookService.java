package com.bridgelabz.BookStoreApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.BookStoreApp.dto.BookDTO;
import com.bridgelabz.BookStoreApp.exception.BookStoreException;
import com.bridgelabz.BookStoreApp.model.BookModel;
import com.bridgelabz.BookStoreApp.model.UserModel;
import com.bridgelabz.BookStoreApp.repository.BookRepository;
import com.bridgelabz.BookStoreApp.repository.UserRepository;
import com.bridgelabz.BookStoreApp.util.TokenUtil;

@Service
public class BookService implements IBookService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    BookRepository bookRepo;

    // Ability to serve to controller's insert api call
    @Override
    public BookModel insertBook(String token, BookDTO bookDTO) throws BookStoreException {
        long id = tokenUtil.decodeToken(token);
        UserModel user = userRepo.findById(id).orElseThrow(() -> new BookStoreException("Token do not match the user"));
        if (user != null) {
            BookModel bookModel = new BookModel(bookDTO);
            return bookRepo.save(bookModel);
        } else {
            throw new BookStoreException("Your not authorize person");
        }

    }

    // Ability to serve to controller's retrieving all records api call
    public List<BookModel> getAllBooks() {
        if (!bookRepo.findAll().isEmpty()) {
            return bookRepo.findAll();
        } else {
            throw new BookStoreException("Books Table is Empty!");
        }

    }

    // Ability to serve to controller's retrieving records by id api call
    public BookModel getBookById(long bookId) throws BookStoreException {
        BookModel bookModel = bookRepo.findById(bookId).orElseThrow(() -> new BookStoreException("Book not found"));
        return bookModel;
    }

    // Ability to serve to controller's update record by id api call
    public BookModel updateBookById(String token, long bookId, BookDTO bookDTO) throws BookStoreException {
        long id = tokenUtil.decodeToken(token);
        userRepo.findById(id).orElseThrow(() -> new BookStoreException("Token do not match the user"));
        bookRepo.findById(bookId).orElseThrow(() -> new BookStoreException("Books id " + bookId + " not found!"));
        BookModel book = new BookModel(bookDTO);
        book.setBookId(bookId);
        return bookRepo.save(book);
    }

    // Ability to serve to controller's delete record by id api call
    public BookModel deleteBookById(String token, long bookId) throws BookStoreException {
        long id = tokenUtil.decodeToken(token);
        UserModel user = userRepo.findById(id).orElseThrow(() -> new BookStoreException("Token do not match the user"));
        BookModel bookModel = bookRepo.findById(bookId)
                .orElseThrow(() -> new BookStoreException("Books id " + bookId + " not found!"));
        if (user != null) {
            bookRepo.deleteById(bookId);
            return bookModel;
        } else
            throw new BookStoreException("User dont have authorization");
    }

    // Ability to serve to controller's update book quantity api call
    public BookModel updateQuantity(String token, long bookId, int quantity) throws BookStoreException {
        long id = tokenUtil.decodeToken(token);
        UserModel user = userRepo.findById(id).orElseThrow(() -> new BookStoreException("Token do not match the user"));
        BookModel book = bookRepo.findById(bookId)
                .orElseThrow(() -> new BookStoreException("Books id " + bookId + " not found!"));
        book.setQuantity(quantity);
        return bookRepo.save(book);
    }

    // Ability to serve to controller's update book price api call
    public BookModel updatePrice(String token, long bookId, int price) throws BookStoreException {
        long id = tokenUtil.decodeToken(token);
        UserModel user = userRepo.findById(id).orElseThrow(() -> new BookStoreException("Token do not match the user"));
        BookModel book = bookRepo.findById(bookId)
                .orElseThrow(() -> new BookStoreException("Books id " + bookId + " not found!"));
        book.setPrice(price);
        return bookRepo.save(book);
    }

}
