package com.bridgelabz.BookStoreApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bridgelabz.BookStoreApp.dto.LoginDTO;
import com.bridgelabz.BookStoreApp.dto.ResponseDTO;
import com.bridgelabz.BookStoreApp.dto.UserDto;
import com.bridgelabz.BookStoreApp.service.IUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bookstore/user")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping(value = {"/", ""})
    public String displayMessage(){
        return "Welcome in Book Store Application";
    }
    
    // API for add user or register user 
    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> registerUser(@Valid @RequestBody UserDto userDto) {
        ResponseDTO response = new ResponseDTO("User Registration Successfully Done",
        userService.registerUser(userDto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // API for get all user 
    @GetMapping("/get")
    public ResponseEntity<ResponseDTO> getAllUser() {
        ResponseDTO response = new ResponseDTO("Users Fetched Successfully Done",
        userService.getAllUser());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

     // API for get user by id
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getByUserId(@PathVariable long id) {
        ResponseDTO response = new ResponseDTO("User Fetched Successfully Done",
        userService.getByUserId(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable long id,
            @Valid @RequestBody UserDto userDto) {
        ResponseDTO response = new ResponseDTO("User Data Updated Successfully",
        userService.updateByUserId(id, userDto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteDetailById(@PathVariable long id) {
        ResponseDTO response = new ResponseDTO("User Data Deleted Successfully ",
        userService.deleteById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

     // API for login user 
     @PostMapping("/login")
     public ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO loginDto) {
         ResponseDTO response = new ResponseDTO("User Login Successfully Done",
         userService.loginUser(loginDto));
         return new ResponseEntity<>(response, HttpStatus.OK);
     }

     // API for forgot password
     @PostMapping("/forgotpassword")
     public ResponseEntity<ResponseDTO> forgotPassword(@RequestBody LoginDTO loginDto) {
         ResponseDTO response = new ResponseDTO("To Update Password Token Generated Successfully",
         userService.forgotPassword(loginDto));
         return new ResponseEntity<>(response, HttpStatus.OK);
     }

     // API for reset password
     @PutMapping("/resetpassword/{token}")
     public ResponseEntity<ResponseDTO> resetPassword(@RequestBody LoginDTO loginDto, @PathVariable String token) {
        ResponseDTO response = new ResponseDTO("Password Reset Done Successfully",
        userService.resetPassword(loginDto, token));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
