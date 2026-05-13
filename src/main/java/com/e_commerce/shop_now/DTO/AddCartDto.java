package com.e_commerce.shop_now.DTO;

import lombok.Data;

@Data
public class AddCartDto {
    private Long productId;
    private int quantity;
}
