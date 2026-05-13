package com.e_commerce.shop_now.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.shop_now.DTO.LoginRequest;
import com.e_commerce.shop_now.Security.JwtUtill;
import com.e_commerce.shop_now.Service.userDetailes;
@RestController
@RequestMapping("/login")
public class LoginController {
@Autowired
private AuthenticationManager auth;
@Autowired
private userDetailes userD;
@Autowired
private JwtUtill util;
@PostMapping("/data")
    public String login(@RequestBody LoginRequest request){
          auth.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
                   
                   UserDetails ud=userD.loadUserByUsername(request.getEmail());
                   String Token=util.generateToken(ud);
return Token;
    }   

}
