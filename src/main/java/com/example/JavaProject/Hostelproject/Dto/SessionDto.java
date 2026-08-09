package com.example.JavaProject.Hostelproject.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SessionDto {

    private String accessToken;
    private String refreshToken;
}
