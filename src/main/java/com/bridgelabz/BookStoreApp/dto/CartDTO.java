package com.bridgelabz.BookStoreApp.dto;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartDTO {
    public long bookId;
    public int quantity;
}
