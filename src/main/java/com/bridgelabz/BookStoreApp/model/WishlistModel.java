package com.bridgelabz.BookStoreApp.model;

import jakarta.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "wishlist")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishlistModel {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long wishlistId;

    @OneToOne(cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public UserModel userModel;

    
    @ManyToOne(cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinColumn(name = "bookId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public BookModel bookModel;


    public WishlistModel(UserModel userModel, BookModel bookModel) {
        this.userModel = userModel;
        this.bookModel = bookModel;
    }

    
}
