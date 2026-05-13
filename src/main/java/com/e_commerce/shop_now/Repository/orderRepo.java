package com.e_commerce.shop_now.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e_commerce.shop_now.Entity.Order;
import com.e_commerce.shop_now.Entity.User;
@Repository
public interface orderRepo extends JpaRepository<Order, Long> {
                 
    public Order findUserByEmail(User user);

    Optional<Order> findByRazorpayOrderId(String orderid);
}
