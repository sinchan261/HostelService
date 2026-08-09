package com.example.JavaProject.Hostelproject.ResponseHandeler;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GlobalResponse<T> {
    private T data;
    private GlobalError error;
   private  LocalDateTime currentTime;
    public GlobalResponse(){
        this.currentTime = LocalDateTime.now();
    }
   public GlobalResponse( GlobalError error1){
        this();
        this.error = error1;
    }
    public GlobalResponse(T data){
        this();
        this.data = data;
    }

}
