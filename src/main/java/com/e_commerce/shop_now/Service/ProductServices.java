package com.e_commerce.shop_now.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_commerce.shop_now.Entity.Product;
import com.e_commerce.shop_now.ExceptionHandler.CustomExe;
import com.e_commerce.shop_now.Repository.productRepo;

@Service
public class ProductServices {
    @Autowired
    private productRepo repo;
    public Product addProduct(Product product){
        if(repo.findByname(product.getName())!=null){
            throw new CustomExe("Product Already Avilable !");
        }
        return repo.save(product);
    }
    public List<Product> getAll() {
        
        return repo.findAll();
    }
    public Product getProductById(Long productId) {
        return repo.findById(productId).orElseThrow(() -> new CustomExe("Product not found with id: " + productId));
    }

}
