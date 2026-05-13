package com.e_commerce.shop_now.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_commerce.shop_now.Entity.User;
import com.e_commerce.shop_now.ExceptionHandler.CustomExe;
import com.e_commerce.shop_now.ExceptionHandler.UserNotFound;
import com.e_commerce.shop_now.Repository.userRepo;

@Service
public class UserServices {
    @Autowired
    private userRepo repo;
    public User add(User user){
                 if(repo.findByUsermail(user.getUsermail())!=null){
                    throw new CustomExe(" User Already Exist !");
                 }
      return repo.save(user);
  }
  public User getById(Long id){
      return repo.findById(id).orElseThrow(()-> new UserNotFound("User Not Found !"));
  }
  public List<User> getAll() {
    return repo.findAll();
  }
  public User getUserByMail(String email){
    return repo.findByUsermail(email);
  }
}
