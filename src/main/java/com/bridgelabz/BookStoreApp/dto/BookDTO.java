package com.bridgelabz.BookStoreApp.dto;

import lombok.Data;

@Data
public class BookDTO {
    private String bookName;
    private String author;
    private String description;
    private String bookImage;
    private int price;
    private int quantity;
}
