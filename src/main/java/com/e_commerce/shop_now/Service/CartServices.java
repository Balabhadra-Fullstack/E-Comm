package com.e_commerce.shop_now.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_commerce.shop_now.Entity.Cart;
import com.e_commerce.shop_now.Entity.CartItems;
import com.e_commerce.shop_now.Entity.Product;
import com.e_commerce.shop_now.Entity.User;
import com.e_commerce.shop_now.Repository.CartItemsRepo;
import com.e_commerce.shop_now.Repository.CartRepo;

@Service
public class CartServices {
    @Autowired
    private CartRepo cartRepository;
    @Autowired
    private ProductServices productService;
    @Autowired
    private CartItemsRepo cartItemsRepo;

public String addToCart(User user, Long productId, int quantity) {
    if(quantity <= 0){
        throw new IllegalArgumentException("Quantity must be greater than zero");
    }
    Cart cart = cartRepository.findByUser(user)
        .orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            newCart.setCartItems(new ArrayList<>());
            newCart.setCreatedDateTime(LocalDateTime.now());
            newCart.setTotalprice(BigDecimal.ZERO);
            return cartRepository.save(newCart);
        });

    Product product = productService.getProductById(productId);
    cartItemsRepo.findByCartAndProduct(cart, product).ifPresentOrElse(cartItem -> {
        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        cartItem.setTotalunitprice(product.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
        cartItemsRepo.save(cartItem);
    }, () -> {
        CartItems newCartItem = new CartItems();
        newCartItem.setProduct(product);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(quantity);
        newCartItem.setUnitprice(product.getPrice());
        newCartItem.setTotalunitprice(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        cartItemsRepo.save(newCartItem);
        cart.getCartItems().add(newCartItem);
    });

    BigDecimal totalCartPrice = cartItemsRepo.findByCart(cart).stream().map(CartItems::getTotalunitprice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

    cart.setTotalprice(totalCartPrice);
    cartRepository.save(cart);
    return "Product added to cart successfully";
}
}
