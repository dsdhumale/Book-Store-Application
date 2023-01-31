package com.bridgelabz.BookStoreApp.model;



import com.bridgelabz.BookStoreApp.dto.BookDTO;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookModel {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId;
    private String bookName;
    private String author;
    private String description;
    private String bookImage;
    private int price;
    private int quantity;

    public BookModel(BookDTO bookDTO) {
        this.bookName = bookDTO.getBookName();
        this.author = bookDTO.getAuthor();
        this.description = bookDTO.getDescription();
        this.bookImage = bookDTO.getBookImage();
        this.price = bookDTO.getPrice();
        this.quantity = bookDTO.getQuantity();
    }
        
}
