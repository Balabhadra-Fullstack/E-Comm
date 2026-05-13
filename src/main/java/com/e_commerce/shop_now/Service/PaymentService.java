package com.e_commerce.shop_now.Service;

import java.math.BigDecimal;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_commerce.shop_now.Config.RazorePayConfig;
import com.e_commerce.shop_now.DTO.PaymentRes;
import com.e_commerce.shop_now.Entity.Order;
import com.e_commerce.shop_now.Entity.OrderItem;
import com.e_commerce.shop_now.Entity.OrderStatus;
import com.e_commerce.shop_now.Entity.Product;
import com.e_commerce.shop_now.Repository.orderRepo;
import com.e_commerce.shop_now.Repository.productRepo;
import com.razorpay.RazorpayClient;
import com.razorpay.Utils;

@Service
public class PaymentService {
@Autowired
 private RazorpayClient razorpayClient;
 @Autowired
 private RazorePayConfig razorePayConfig;
   @Autowired
   private orderRepo oRepo;
   @Autowired
   private productRepo repo;
    private String keySecret=razorePayConfig.getKeySecret();
    public PaymentRes processPayment(Order order) {
        // Logic to process payment using Razorpay API
        try {
            // Create a payment order in Razorpay
                 JSONObject options = new JSONObject();
            BigDecimal amountInPaise = order.getTotalAmount().multiply(BigDecimal.valueOf(100));
            options.put("amount", amountInPaise.longValue());
            options.put("currency", "INR");
            options.put("receipt", "order_rcptid_" + System.currentTimeMillis());
            options.put("payment_capture", 1); // auto capture

            com.razorpay.Order razorpayOrder = razorpayClient.orders.create(options);
                   
            PaymentRes paymentRes = new PaymentRes();
            paymentRes.setOrderId(order.getId()+"");
            paymentRes.setRazorpayPaymentId(razorpayOrder.get("id"));
            order.setRazorpayOrderId(razorpayOrder.get("id"));
            oRepo.save(order);
            return paymentRes;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Payment processing failed");
        }
    }

    public boolean verifyPayment(String paymentid,String orderid,String signeture){
        try {
            JSONObject options = new JSONObject();
            options.put("razorpay_payment_id", paymentid);
            options.put("razorpay_order_id", orderid);
            options.put("razorpay_signature", signeture);
            boolean isValid = Utils .verifyPaymentSignature(options, keySecret);
                Order order = oRepo.findByRazorpayOrderId(orderid).orElseThrow(() -> new RuntimeException("Order not found"));
                if(isValid){
                    order.setStatus(OrderStatus.CONFIRMED);
                   order.setRazorpayPaymentId(paymentid);
                   order.setPaymentStatus("PAID");
                  for(OrderItem item:order.getOrderItems()){
                    Product product=  item.getProduct();
                    int stockExist=product.getStockQuantity()-item.getQuantity();
                    product.setStockQuantity(stockExist);
                    repo.save(product);
                  }
            oRepo.save(order);
                }
            return isValid;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
