package com.bridgelabz.BookStoreApp.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    
    private String email;
    private String password;
}
