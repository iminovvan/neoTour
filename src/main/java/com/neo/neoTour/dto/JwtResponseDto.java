package com.neo.neoTour.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class JwtResponseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
