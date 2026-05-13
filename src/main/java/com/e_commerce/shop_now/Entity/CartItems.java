package com.e_commerce.shop_now.Entity;
import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "product_id")
private Product product;
  @ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "cart_id")
private Cart cart;
    private int quantity;
    private BigDecimal unitprice;
    private BigDecimal totalunitprice;
}
