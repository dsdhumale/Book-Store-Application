package com.bridgelabz.BookStoreApp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message = "Entered first name is invalid, Please enter valid first name")
    private String firstName;
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message = "Entered last name is invalid, Please enter valid last name")
    private String lastName;
    @Email(message = "Invalid Email ID")
    private String email;
    private String password;
}
