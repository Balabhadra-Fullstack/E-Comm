package com.e_commerce.shop_now.Repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.e_commerce.shop_now.Entity.Order;
import com.e_commerce.shop_now.Entity.OrderItem;
import com.e_commerce.shop_now.Entity.Product;
@Repository
public interface orderItemsRepo extends CrudRepository<OrderItem, Long> {
    Optional<OrderItem> findByOrderAndProduct(Order order, Product product);
}