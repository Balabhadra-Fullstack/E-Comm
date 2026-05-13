package com.e_commerce.shop_now.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.shop_now.DTO.PaymentRes;
import com.e_commerce.shop_now.DTO.PaymentValidation;
import com.e_commerce.shop_now.Entity.Order;
import com.e_commerce.shop_now.Service.OrderService;
import com.e_commerce.shop_now.Service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/payment")
@PreAuthorize("hasRole('USER','ADMIN')")
public class PaymentController {  
@Autowired
private PaymentService paymentService;
@Autowired
private OrderService orderService;
@PostMapping("/{orderId}")
 public PaymentRes processPayment(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
      PaymentRes res=  paymentService.processPayment(order);
         if(res!=null){
           return res;
         }else{
            throw new RuntimeException("Payment processing failed");
         } 
 }
@PostMapping("/verify")
public boolean verifyPayment(@RequestBody PaymentValidation validation ) {
       
        return paymentService.verifyPayment(validation.getRazorpayPaymentId(), validation.getRazorpayOrderId(), validation.getRazorpaySignature());
    }





}