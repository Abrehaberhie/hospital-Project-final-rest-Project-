package com.binary.hospital.Dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticationResponseDto {

    private final String token;

    public AuthenticationResponseDto(String token) {
        this.token = token;
    }

}
