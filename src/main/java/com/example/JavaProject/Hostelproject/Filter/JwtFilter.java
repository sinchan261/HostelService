package com.example.JavaProject.Hostelproject.Filter;

import com.example.JavaProject.Hostelproject.Entity.UserEntity;
import com.example.JavaProject.Hostelproject.Services.JwtServices;
import com.example.JavaProject.Hostelproject.Services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.time.Duration;

@Configuration
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    JwtServices jwtServices;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @Autowired()
    @Qualifier("handlerExceptionResolver")
    HandlerExceptionResolver handlerExceptionResolver;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String requestToken =  request.getHeader("Authorization");
        try{
            if (requestToken == null || !requestToken.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }
            String token = request.getHeader("Authorization").split(" ")[1];

            Long id = jwtServices.getUserIdvalueFromToken(token);

            if (id != null && SecurityContextHolder.getContext().getAuthentication() == null) {

//                we have to implements cache here
                String key = "authUser"+id+":";
                UserEntity userEntity;
                if( redisTemplate.opsForValue().get(key) ==null){
                     userEntity = userService.getUserById(id);
                    redisTemplate.opsForValue().set(key,userEntity, Duration.ofDays(1));
                    System.out.println("UserEntity is not catached: "+userEntity);
                }else{
                   Object object=  redisTemplate.opsForValue().get(key);
                   userEntity = modelMapper.map(object, UserEntity.class);
                   System.out.println("UserEntity is now catached: "+userEntity.getName());
                }


                Authentication authentication = new UsernamePasswordAuthenticationToken(userEntity, null, userEntity.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
  filterChain.doFilter(request,response);
        } catch (Exception e) {
            handlerExceptionResolver.resolveException(request,response,null,e);
        }

    }
}
