package com.e_commerce.shop_now.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import lombok.Data;
@Configuration
@Data
public class RazorePayConfig {
    private String keyId="rzp_test_1DP5mmOlF5G5ag";
    private String keySecret="rzp_test_1DP5mmOlF5G5ag";

    @Bean
    public RazorpayClient razorpayClient() throws RazorpayException {
        return new RazorpayClient(keyId, keySecret);
    }

}
