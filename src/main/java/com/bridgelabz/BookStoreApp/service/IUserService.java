package com.bridgelabz.BookStoreApp.service;

import java.util.List;

import com.bridgelabz.BookStoreApp.dto.LoginDTO;
import com.bridgelabz.BookStoreApp.dto.UserDto;
import com.bridgelabz.BookStoreApp.model.UserModel;

import jakarta.validation.Valid;

public interface IUserService {

    public UserModel registerUser(@Valid UserDto userDto);

    public List<UserModel> getAllUser();

    public UserModel getByUserId(long id);

    public UserModel updateByUserId(long id, @Valid UserDto userDto);

    public UserModel deleteById(long id);

    public String loginUser(LoginDTO loginDto);

    public String forgotPassword(LoginDTO loginDto);

    public UserModel resetPassword(LoginDTO loginDto,String token);

    
}
