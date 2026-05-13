package com.e_commerce.shop_now.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.shop_now.DTO.AddCartDto;
import com.e_commerce.shop_now.Entity.User;
import com.e_commerce.shop_now.Repository.userRepo;
import com.e_commerce.shop_now.Service.CartServices;
import org.springframework.web.bind.annotation.PostMapping;

@RequestMapping("/cart")
@RestController
public class AddCartsController {
    @Autowired
    private CartServices cartService;
    @Autowired
    private userRepo userRepository;
    @PreAuthorize("hasRole('USER','ADMIN')")
    @PostMapping("/add") 
    public String addToCart(@RequestBody AddCartDto addCartDto,Principal principal){
                    User u  = userRepository.findByUsername(principal.getName());

        cartService.addToCart(u, addCartDto.getProductId(), addCartDto.getQuantity());
        return "Product added to cart successfully";
    }
   

    
}
