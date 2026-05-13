package com.e_commerce.shop_now.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_commerce.shop_now.Entity.Category;
import com.e_commerce.shop_now.ExceptionHandler.CustomExe;
import com.e_commerce.shop_now.Repository.CatRepo;

@Service
public class CategoryServices {
@Autowired
private CatRepo repo;
    public Category add(Category cat){
             if(repo.findByCategoryname(cat.getCategoryname())!=null){
                throw new CustomExe("Already Exist !");
             }
           return repo.save(cat);
    }
    public Category getById(Long id){
        return repo.findById(id).orElseThrow(()->new CustomExe("Not Found !"));
    }
    public List< Category> getAll(){
        return repo.findAll();
    }

}
