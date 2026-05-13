package com.e_commerce.shop_now.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.shop_now.DTO.UserDto;
import com.e_commerce.shop_now.Entity.User;
import com.e_commerce.shop_now.ExceptionHandler.CustomExe;
import com.e_commerce.shop_now.Service.UserServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/users")

public class UserController {
    
    @Autowired
    private UserServices serv;
   @Autowired
   private PasswordEncoder passwordEncoder;
    @PostMapping("/add")
public ResponseEntity<?> addUser(@RequestBody UserDto uDto)
{        
         User user =serv.getUserByMail(uDto.getEmail());
    if(user != null){
        throw new CustomExe("User Already Exists !");
    }
    user = new User();
    user.setUsername(uDto.getUsername());
    user.setUsermail(uDto.getEmail());
    user.setUserpassword(passwordEncoder.encode(uDto.getUserpassword()));
    user.setRole("USER");
    User adduser=serv.add(user);
    return ResponseEntity.ok(adduser);
}
@GetMapping("/{id}")
public ResponseEntity<User> getUserById(@PathVariable Long id){
    User user=serv.getById(id);
    return ResponseEntity.ok(user);
}
@GetMapping("/all")
@PreAuthorize("hasRole('ADMIN')")
public List<User> getAllUsers(){
    List<User> users=serv.getAll();
    return users;


}
}
