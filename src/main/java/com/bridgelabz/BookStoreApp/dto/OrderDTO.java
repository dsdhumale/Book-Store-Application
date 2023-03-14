package com.bridgelabz.BookStoreApp.dto;

import java.time.LocalDate;
import java.util.List;

import com.bridgelabz.BookStoreApp.model.BookModel;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

@Data
public class OrderDTO {
    @PastOrPresent(message = "Order date should be past or present!")
    private LocalDate orderDate;
    private long price;
    private int quantity;
    @NotNull(message = "Address can not be null!")
    private String address;
    private List<Object> book;
    private boolean cancel;
    
}
