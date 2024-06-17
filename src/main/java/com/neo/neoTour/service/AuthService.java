package com.neo.neoTour.service;

import com.neo.neoTour.dto.JwtRequestDto;
import com.neo.neoTour.dto.JwtResponseDto;
import com.neo.neoTour.exception.InvalidCredentialException;
import com.neo.neoTour.util.CustomUserDetails;
import com.neo.neoTour.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetails customUserDetails;

    public ResponseEntity<JwtResponseDto> authentication(JwtRequestDto authRequest){
        if (authRequest == null || authRequest.getPhoneNumber() == null || authRequest.getPassword() == null) {
            throw new InvalidCredentialException("Invalid input: phone number or password is null");
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getPhoneNumber(), authRequest.getPassword()));
        } catch (BadCredentialsException e){
            throw new InvalidCredentialException("Invalid username or password");

        }
        UserDetails userDetails = customUserDetails.loadUserByUsername(authRequest.getPhoneNumber());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponseDto(token));
    }
}
