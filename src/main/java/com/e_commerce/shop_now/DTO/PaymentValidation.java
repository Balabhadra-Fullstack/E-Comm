package com.e_commerce.shop_now.DTO;

import lombok.Data;

@Data
public class PaymentValidation {
    private String razorpayPaymentId;
    private String razorpayOrderId;
    private String razorpaySignature;
}
