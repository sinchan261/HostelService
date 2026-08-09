package com.example.JavaProject.Hostelproject.ResponseHandeler;

import org.jspecify.annotations.Nullable;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
@RestControllerAdvice
public class GlobalResponseHandeler implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public @Nullable Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if( body instanceof GlobalError errorv ){
            System.out.println(body);
            return new GlobalResponse<>( errorv );
        }
//        if( body instanceof GlobalResponse<?>){
//            return body;
//        }
        return new GlobalResponse<>(body);
    }
}
