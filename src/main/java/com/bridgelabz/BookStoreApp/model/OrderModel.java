package com.bridgelabz.BookStoreApp.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "yourorder")
public class OrderModel {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    LocalDate orderDate;
    private double price;
    private int quantity;
    private String address;

    @OneToOne(cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private UserModel user;

    @ManyToOne(cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinColumn(name = "bookId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private BookModel book;
    private boolean cancel;
    
    public OrderModel(LocalDate orderDate, double price, int quantity, String address, UserModel user,
            BookModel book, boolean cancel) {
        this.orderDate = orderDate;
        this.price = price;
        this.quantity = quantity;
        this.address = address;
        this.user = user;
        this.book = book;
        this.cancel = cancel;
    }

}
