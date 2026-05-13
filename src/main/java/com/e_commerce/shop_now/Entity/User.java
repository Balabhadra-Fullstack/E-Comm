package com.e_commerce.shop_now.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "Users")
public class User {
@jakarta.persistence.Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long userid;
private String username;
private String usermail;
private String userpassword;
private String role;
}
