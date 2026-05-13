package com.e_commerce.shop_now.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.e_commerce.shop_now.Entity.User;
import com.e_commerce.shop_now.Repository.userRepo;
@Component
public class userDetailes implements UserDetailsService{
    @Autowired
    private userRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                              User user=repo.findByUsermail(username);
            if(user==null){
                throw new UsernameNotFoundException("User not found with username: " + username);
            }
                return new org.springframework.security.core.userdetails.User(user.getUsermail(), user.getUserpassword(),List.of(new SimpleGrantedAuthority(user.getRole())));                     

    }
    }
  

 


