package com.e_commerce.shop_now.DTO;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
