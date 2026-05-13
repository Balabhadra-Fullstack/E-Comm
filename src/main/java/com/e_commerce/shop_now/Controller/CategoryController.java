package com.e_commerce.shop_now.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.shop_now.Entity.Category;
import com.e_commerce.shop_now.Service.CategoryServices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/categories")
@PreAuthorize("hasRole('ADMIN')")
public class CategoryController {
    @Autowired
   private CategoryServices serv;
   @PostMapping("/addCategory")
   @PreAuthorize("hasRole('ADMIN')")
public Category addCategory(@RequestBody Category cat){
    return serv.add(cat); 
}
@GetMapping("/all")
@PreAuthorize("hasRole('ADMIN','USER')")
public List<Category> getAll(){
    return serv.getAll();
}
}
