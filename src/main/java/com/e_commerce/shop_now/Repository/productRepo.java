package com.e_commerce.shop_now.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e_commerce.shop_now.Entity.Category;
import com.e_commerce.shop_now.Entity.Product;
import java.util.List;

@Repository
public interface productRepo extends JpaRepository<Product,Long> {
    public List<Product> findByCategory(Category category);
    public Product findByname(String name);
    
}
