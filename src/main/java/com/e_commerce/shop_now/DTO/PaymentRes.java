package com.e_commerce.shop_now.DTO;

import lombok.Data;

@Data
public class PaymentRes {
    private String orderId;
    private String RazorpayPaymentId;
    
}
