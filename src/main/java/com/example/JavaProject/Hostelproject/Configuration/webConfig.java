package com.example.JavaProject.Hostelproject.Configuration;

import com.example.JavaProject.Hostelproject.Enum.UserTypeEnum;
import com.example.JavaProject.Hostelproject.Filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class webConfig {
    @Autowired
    JwtFilter jwtFilter;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        httpSecurity.authorizeHttpRequests(e->{
            e.requestMatchers("/auth/login","/auth/signup").permitAll()
                    .requestMatchers("/auth/student/**").hasAuthority("STUDENT")
                    .requestMatchers("/auth/**").authenticated();
        }).formLogin(e->e.disable()).csrf(e->e.disable())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
