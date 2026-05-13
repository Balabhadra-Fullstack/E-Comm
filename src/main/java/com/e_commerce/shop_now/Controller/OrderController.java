package com.e_commerce.shop_now.Controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.shop_now.DTO.OrderRequest;
import com.e_commerce.shop_now.DTO.OrderResonse;
import com.e_commerce.shop_now.Entity.User;
import com.e_commerce.shop_now.Repository.userRepo;
import com.e_commerce.shop_now.Service.OrderService;

@RestController
@RequestMapping("/orders")
@PreAuthorize("hasRole('USER','ADMIN')")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private userRepo uRepo;
    @PostMapping("/placed")
    public OrderResonse placeOrder(@RequestBody OrderRequest orderRequest,Principal principal) {
    
                    String name=principal.getName();     
           User user=uRepo.findByUsername(name);
         return  orderService.placeOrder(user,orderRequest.shippingAddress,orderRequest.billingAddress);
                         
}
    
}
