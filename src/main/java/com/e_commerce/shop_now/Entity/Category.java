package com.e_commerce.shop_now.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "Category")
public class Category {
@jakarta.persistence.Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String categoryname;
private String description;
}
