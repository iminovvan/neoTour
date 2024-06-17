package com.neo.neoTour.controller.admin;

import com.neo.neoTour.dto.JwtRequestDto;
import com.neo.neoTour.dto.JwtResponseDto;
import com.neo.neoTour.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AdminAuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody JwtRequestDto authRequest){
        return authService.authentication(authRequest);
    }
}
