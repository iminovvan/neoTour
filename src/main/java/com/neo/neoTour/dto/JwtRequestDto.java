package com.neo.neoTour.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class JwtRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String phoneNumber;
    private String password;
}
