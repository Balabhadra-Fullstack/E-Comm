package com.e_commerce.shop_now.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e_commerce.shop_now.Entity.Category;

@Repository
public interface CatRepo extends JpaRepository<Category,Long> {
    public Category findByCategoryname(String categoryname);
    public List<Category> findAll();

}
