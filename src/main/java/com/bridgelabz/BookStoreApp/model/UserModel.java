package com.bridgelabz.BookStoreApp.model;

import java.time.LocalDate;

import com.bridgelabz.BookStoreApp.dto.UserDto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate dob;
    private Boolean verify;
    private LocalDate registeredDate;
    private LocalDate updatedDate;
    private LocalDate purchaseDate;
    private LocalDate expiryDate;

    public UserModel(UserDto userDto) {
        this.firstName = userDto.getFirstName();
        this.lastName = userDto.getLastName();
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
        this.dob = userDto.getDob();
        this.verify = userDto.getVerify();
        this.registeredDate = userDto.getRegisteredDate();
        this.updatedDate = userDto.getUpdatedDate();
    }

    
    
}
