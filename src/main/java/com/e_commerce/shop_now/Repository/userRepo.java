package com.e_commerce.shop_now.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e_commerce.shop_now.Entity.User;
@Repository
    public interface userRepo extends JpaRepository<com.e_commerce.shop_now.Entity.User, Long> 
   {
  public User findByUsermail(String usermail);
    public User findByUsername(String username);
   }

