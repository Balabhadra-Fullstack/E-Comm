package com.e_commerce.shop_now.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e_commerce.shop_now.Entity.Cart;
import com.e_commerce.shop_now.Entity.CartItems;
import com.e_commerce.shop_now.Entity.Product;
@Repository
public interface CartItemsRepo extends JpaRepository<CartItems,Long> {
    Optional<CartItems> findByCartAndProduct(Cart cart, Product product);
    List<CartItems> findByCart(Cart cart);
    
}
