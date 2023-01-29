package com.bridgelabz.BookStoreApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.BookStoreApp.dto.LoginDTO;
import com.bridgelabz.BookStoreApp.dto.UserDto;
import com.bridgelabz.BookStoreApp.exception.BookStoreException;
import com.bridgelabz.BookStoreApp.model.UserModel;
import com.bridgelabz.BookStoreApp.repository.UserRepository;
import com.bridgelabz.BookStoreApp.util.EmailSenderUtil;
import com.bridgelabz.BookStoreApp.util.TokenUtil;

import jakarta.validation.Valid;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
	private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    EmailSenderUtil emailSender;



    @Override
    public UserModel registerUser(@Valid UserDto userDto) {
        Optional<UserModel> data = userRepo.findByEmailId(userDto.getEmail());
        System.out.print("data" + data);
        if (data.isPresent()) {
            throw new BookStoreException("User Email ID already registered, Please enter another one");
        } else {
            String hashPassword= passwordEncoder.encode(userDto.getPassword());
            userDto.setPassword(hashPassword);
            UserModel userModel = new UserModel(userDto);
            userRepo.save(userModel);
            return userModel;
        }

    }

    @Override
    public List<UserModel> getAllUser() {
        List<UserModel> userData = userRepo.findAll();
        if (userData.isEmpty()) {
            throw new BookStoreException("User not created till now");
        } else {
            return userData;
        }
    }

    @Override
    public UserModel getByUserId(long id) throws BookStoreException {
        UserModel userData = userRepo.findById(id)
                .orElseThrow(() -> new BookStoreException("User data not found for id " + id));
        return userData;
    }

    @Override
    public UserModel updateByUserId(long id, @Valid UserDto userDto) throws BookStoreException {
        UserModel userData = userRepo.findById(id)
                .orElseThrow(() -> new BookStoreException("User data not found for id " + id));
        userData.setFirstName(userDto.getFirstName());
        userData.setLastName(userDto.getLastName());
        userData.setEmail(userDto.getEmail());
        String hashPassword= passwordEncoder.encode(userDto.getPassword());
        userData.setPassword(hashPassword);
        return userRepo.save(userData);
    }

    @Override
    public UserModel deleteById(long id) {
        if (userRepo.findById(id).isPresent()) {
            UserModel userData = userRepo.findById(id).get();
            userRepo.deleteById(id);
            return userData;
        } else {
            throw new BookStoreException("Address book data not found for id " + id);
        }
    }

    @Override
    public String loginUser(LoginDTO loginDto) {
        Optional<UserModel> data = userRepo.findByEmailId(loginDto.getEmail());
        if (data.isPresent()) {   
            if (passwordEncoder.matches(loginDto.getPassword(),data.get().getPassword())) {
                String token = tokenUtil.createToken(loginDto.getEmail());
                return token;
            } else {
                throw new BookStoreException("Password is incorrect, Enter valid password");
            }
        } else {
            throw new BookStoreException("User not found, Enter valid email");
        }
    }

    @Override
    public String forgotPassword(LoginDTO loginDto) {
        Optional<UserModel> data = userRepo.findByEmailId(loginDto.getEmail());
        if (data.isPresent()) {
            String token = tokenUtil.createToken(loginDto.getEmail());
            return token;
        } else {
            throw new BookStoreException("User not found, Enter valid email");
        }

    }

    @Override
    public UserModel resetPassword(LoginDTO loginDto, String token) {
        String email = tokenUtil.decodeToken(token);
        Optional<UserModel> data = userRepo.findByEmailId(email);
        
        if (data.isPresent()) {
            String hashPassword= passwordEncoder.encode(loginDto.getPassword());
            data.get().setPassword(hashPassword);
            return userRepo.save(data.get());
        } else {
            throw new BookStoreException("Invalid token");
        }
        
    }

}
