package com.bridgelabz.BookStoreApp.model;

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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    public UserModel userModel;

    @ManyToOne
    @JoinColumn(name = "bookId")
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
