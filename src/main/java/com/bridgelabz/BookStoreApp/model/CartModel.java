package com.bridgelabz.BookStoreApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartModel {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long cartId;

    @OneToOne(cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public UserModel userModel;

    
    @ManyToOne(cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinColumn(name = "bookId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public BookModel bookModel;

    public int quantity;
    public double totalPrice;

    public CartModel(UserModel userModel, BookModel bookModel, int quantity, double totalPrice) {
        this.userModel = userModel;
        this.bookModel = bookModel;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

}
