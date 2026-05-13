package com.e_commerce.shop_now.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e_commerce.shop_now.DTO.OrderResonse;
import com.e_commerce.shop_now.Entity.Cart;
import com.e_commerce.shop_now.Entity.CartItems;
import com.e_commerce.shop_now.Entity.Order;
import com.e_commerce.shop_now.Entity.OrderItem;
import com.e_commerce.shop_now.Entity.OrderStatus;
import com.e_commerce.shop_now.Entity.User;
import com.e_commerce.shop_now.ExceptionHandler.CustomExe;
import com.e_commerce.shop_now.Repository.CartRepo;
import com.e_commerce.shop_now.Repository.orderItemsRepo;
import com.e_commerce.shop_now.Repository.orderRepo;



@Service
public class OrderService {
  @Autowired
  private CartRepo cartRepo;
  @Autowired
  private orderRepo oRepo;
  @Autowired
  private orderItemsRepo orderItemsRepo;
@Transactional
    public OrderResonse placeOrder(User user, String shippingAddress, String billingAddress) {
        // Logic to place an order for the user with the provided shipping and billing addresses
           Cart cart= cartRepo.findByUser(user).orElseThrow(()-> new CustomExe("Cart Not found"));
           if(cart.getCartItems() == null || cart.getCartItems().isEmpty()){
            throw new RuntimeException("No Cart Items");
           }
            Order order = new Order();
            order.setUser(user);
            order.setShippingAddress(shippingAddress);
            order.setBillingAddress(billingAddress);
            order.setTotalAmount(BigDecimal.ZERO);
            order.setStatus(OrderStatus.PENDING);
            order.setOrderItems(new ArrayList<>());
            order.setRazorpayOrderId(null);
            order.setRazorpayPaymentId(null);
            order.setPaymentStatus(null);
            oRepo.save(order);
            for (CartItems cartItem : cart.getCartItems()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setProduct(cartItem.getProduct());
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setUnitPrice(cartItem.getUnitprice());
                orderItem.setTotalPrice(cartItem.getUnitprice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
                orderItemsRepo.save(orderItem);
                order.getOrderItems().add(orderItem);
                order.setTotalAmount(order.getTotalAmount().add(orderItem.getTotalPrice()));
            }
            oRepo.save(order);
            OrderResonse orderResponse = new OrderResonse();
            orderResponse.setOrderId(order.getId());
            orderResponse.setStatus(order.getStatus().name());
            orderResponse.setTotalAmount(order.getTotalAmount());
            return orderResponse;
          }
public Order getOrderById(Long orderId) {
    return oRepo.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
}
            

        }
      