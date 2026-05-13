package com.e_commerce.shop_now.Security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.e_commerce.shop_now.Service.userDetailes;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class FilterClass extends OncePerRequestFilter {
    @Autowired  
    private JwtUtill jwtUtil;
    @Autowired
    private userDetailes userDetails;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token=authHeader.substring(7);
            String username=jwtUtil.extractusername(token);
            if(username!=null){
                    UserDetails user=userDetails.loadUserByUsername(username);
                    if(jwtUtil.validateToken(token, user)){
                     UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                     SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
            }
    }
        filterChain.doFilter(request, response);
}
  
}
