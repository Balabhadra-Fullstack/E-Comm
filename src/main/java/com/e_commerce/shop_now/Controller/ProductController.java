package com.e_commerce.shop_now.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.e_commerce.shop_now.DTO.ProductDTO;
import com.e_commerce.shop_now.Entity.Category;
import com.e_commerce.shop_now.Entity.Product;
import com.e_commerce.shop_now.Service.CategoryServices;
import com.e_commerce.shop_now.Service.ProductServices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductServices serv;
    @Autowired
    private CategoryServices catserv;
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public Product addProduct(@RequestBody ProductDTO pro){
        Product pro1=new Product();
        pro1.setName(pro.getName());
        pro1.setDescription(pro.getDescription());
        pro1.setPrice(pro.getPrice());
        pro1.setStockQuantity(pro.getStockQuantity());
        pro1.setImageUrl(pro.getImageUrl());
                Category cat=catserv.getById(pro.getCategoryId());
        pro1.setCategory(cat);
        return serv.addProduct(pro1);
    }
    @PreAuthorize("hasRole('ADMIN','USER')")
    @GetMapping("/all")
    public List<Product> getall(){
        return serv.getAll();
    }
}

