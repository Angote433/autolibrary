package com.arnold.autolibrary.security;

import com.arnold.autolibrary.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Controller
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        String token = null;
        String userName = null;

        //header exists?
        if(authHeader != null &&  authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            try{
                userName = jwtUtil.extractUserName(token);
            } catch (Exception e) {}
        }

        //valid username
        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){

            try{
                if(!jwtUtil.isTokenExpired(token)){
                    String role = jwtUtil.extractRole(token);

                    //security auth object
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userName,null
                                    , List.of(new SimpleGrantedAuthority("ROLE "+ role)));

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }catch (Exception e) {
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(request , response);

    }
}
