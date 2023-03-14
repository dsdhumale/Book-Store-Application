package com.bridgelabz.BookStoreApp.service;

import java.util.List;

import com.bridgelabz.BookStoreApp.dto.OrderDTO;
import com.bridgelabz.BookStoreApp.model.OrderModel;

public interface IOrderService {

    public OrderModel placeOrder(OrderDTO orderDto,String token);

    public List<OrderModel> getAll();

    public OrderModel cancelOrder(String token, long id);

    public List<OrderModel> getByUserId(String token);

}
