package com.e_commerce.shop_now.DTO;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class OrderResonse {
    private long orderId;
    private String status;
    private BigDecimal totalAmount;
    
}
