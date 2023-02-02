package com.bridgelabz.BookStoreApp.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.BookStoreApp.dto.OrderDTO;
import com.bridgelabz.BookStoreApp.exception.BookStoreException;
import com.bridgelabz.BookStoreApp.model.BookModel;
import com.bridgelabz.BookStoreApp.model.OrderModel;
import com.bridgelabz.BookStoreApp.model.UserModel;
import com.bridgelabz.BookStoreApp.repository.BookRepository;
import com.bridgelabz.BookStoreApp.repository.CartRepository;
import com.bridgelabz.BookStoreApp.repository.OrderRepository;
import com.bridgelabz.BookStoreApp.repository.UserRepository;
import com.bridgelabz.BookStoreApp.util.EmailSenderUtil;
import com.bridgelabz.BookStoreApp.util.TokenUtil;

@Service
public class OrderService implements IOrderService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    BookRepository bookRepo;

    @Autowired
    CartRepository cartRepo;

    @Autowired
    OrderRepository orderRepo;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    EmailSenderUtil emailSender;

    @Override
    public OrderModel placeOrder(OrderDTO orderDto) {
        UserModel user = userRepo.findById(orderDto.getUser())
                .orElseThrow(() -> new BookStoreException("User not found"));
        BookModel book = bookRepo.findById(orderDto.getBook())
                .orElseThrow(() -> new BookStoreException("book not found"));
        if (book.getQuantity() >= orderDto.getQuantity()) {
            double price = orderDto.getQuantity() * book.getPrice();
            LocalDate orderDate = LocalDate.now();
            OrderModel order = new OrderModel(orderDate, price, orderDto.getQuantity(), orderDto.getAddress(), user,
                    book, false);
            OrderModel orderData = orderRepo.save(order);
            emailSender.sendEmail(user.getEmail(), "Order Placed",
                    "Your order placed successfully and order id is " + orderData.getOrderId());
            return orderData;
        } else {
            throw new BookStoreException("Out of Stock");
        }
    }

    @Override
    public List<OrderModel> getAll() {
        List<OrderModel> order = orderRepo.findAll();
        return order;
    }

    @Override
    public OrderModel cancelOrder(String token, long id) {
        long userid = tokenUtil.decodeToken(token);
        UserModel user = userRepo.findById(userid).orElseThrow(() -> new BookStoreException("User not found"));
        OrderModel order = orderRepo.findById(id).orElseThrow(() -> new BookStoreException("Order not found"));
        order.setCancel(true);
        return orderRepo.save(order);
    }

    @Override
    public List<OrderModel> getByUserId(String token) {
        long userId = tokenUtil.decodeToken(token);
        List<OrderModel> order = orderRepo.findByUserId(userId);
        if (order != null) {
            return order;
        } else {
            throw new BookStoreException("For given user id " + userId + " data not found ");
        }
    }

}
