package com.bridgelabz.BookStoreApp.model;

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

    public UserModel(UserDto userDto) {
        this.firstName = userDto.getFirstName();
        this.lastName = userDto.getLastName();
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
    }
    
}
