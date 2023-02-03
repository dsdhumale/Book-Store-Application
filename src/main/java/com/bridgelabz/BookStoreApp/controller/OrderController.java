package com.bridgelabz.BookStoreApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bridgelabz.BookStoreApp.dto.OrderDTO;
import com.bridgelabz.BookStoreApp.dto.ResponseDTO;
import com.bridgelabz.BookStoreApp.service.IOrderService;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/bookstore/order")
public class OrderController {

    @Autowired
    IOrderService orderService;

    /*
     * API for place order
     * http://localhost:8080/bookstore/order/place
     */
    @PostMapping("/place")
    public ResponseEntity<ResponseDTO> placeOrder(@RequestBody OrderDTO orderDto) {
        ResponseDTO responseDTO = new ResponseDTO("Order Placed Successfully", orderService.placeOrder(orderDto));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    /*
     * API for get all order
     * http://localhost:8080/bookstore/order/getall
     */
    @GetMapping("/getall")
    public ResponseEntity<ResponseDTO> getAll() {
        ResponseDTO responseDTO = new ResponseDTO("Here are all the Order Items...", orderService.getAll());
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /*
     * API for cancel order by id
     * http://localhost:8080/bookstore/order/cancelorder/1
     */
    @PutMapping("/cancelorder/{id}")
    public ResponseEntity<ResponseDTO> cancelOrder(@RequestHeader(name = "Authorization") String token,
            @PathVariable long id) {
        ResponseDTO responseDTO = new ResponseDTO("Order Successfully cancelled", orderService.cancelOrder(token, id));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.ACCEPTED);
    }

    /*
     * API for get perticular order by user id
     * http://localhost:8080/bookstore/order/getbyuser
     */
    @GetMapping("/getbyuser")
    public ResponseEntity<ResponseDTO> getByUserId(@RequestHeader(name = "Authorization") String token) {
        ResponseDTO responseDTO = new ResponseDTO("Order fetched for particular user successfully",
                orderService.getByUserId(token));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /*
     * API for delete perticular order by order id
     * http://localhost:8080/bookstore/order/delete/2
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteById(@RequestHeader(name = "Authorization") String token,@PathVariable long id) {
        ResponseDTO responseDTO = new ResponseDTO("Order Deleted Successfully",
                orderService.deleteById(token,id));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /*
     * API for update order by id
     * http://localhost:8080/bookstore/order/update/1
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateOrder(@PathVariable long id,@RequestBody OrderDTO orderDto ) {
        ResponseDTO responseDTO = new ResponseDTO("Order Successfully cancelled", orderService.updateOrder(id,orderDto));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.ACCEPTED);
    }

}
