package com.arnold.autolibrary.config;

import com.arnold.autolibrary.security.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthFilter authFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http
                .csrf( csrf->csrf.disable())
                .sessionManagement(session -> session.
                        sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth ->auth
                        //no token needed
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/users/**").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.POST, "/api/books/**")
                        .hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.POST, "/api/classes/**")
                        .hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.POST, "/api/streams/**")
                        .hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.PUT, "/api/losses/**")
                        .hasRole("LIBRARIAN")

                        //both users can access these endpoints
                        .requestMatchers("/api/students/**")
                        .hasAnyRole("LIBRARIAN", "TEACHER")
                        .requestMatchers("/api/distributions/**")
                        .hasAnyRole("LIBRARIAN", "TEACHER")
                        .requestMatchers("/api/borrows/**")
                        .hasAnyRole("LIBRARIAN", "TEACHER")
                        .requestMatchers(HttpMethod.GET, "/api/books/**")
                        .hasAnyRole("LIBRARIAN", "TEACHER")
                        .requestMatchers(HttpMethod.GET, "/api/losses/**")
                        .hasAnyRole("LIBRARIAN", "TEACHER")
                        .requestMatchers(HttpMethod.GET, "/api/classes/**")
                        .hasAnyRole("LIBRARIAN", "TEACHER")
                        .requestMatchers(HttpMethod.GET, "/api/streams/**")
                        .hasAnyRole("LIBRARIAN", "TEACHER")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(authFilter,
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
