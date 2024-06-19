package com.neo.neoTour.controller.admin;

import com.neo.neoTour.dto.JwtRequestDto;
import com.neo.neoTour.dto.JwtResponseDto;
import com.neo.neoTour.service.AuthService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Admin Authentication",
        description = "Endpoint to authenticate admins"
)
@RestController
@RequiredArgsConstructor
@Hidden
@RequestMapping("/auth")
public class AdminAuthController {
    private final AuthService authService;
    @Operation(
            summary = "Login for admins",
            description = "Admin user can login to access private endpoints"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Invalid phone number or password")
    })
    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody JwtRequestDto authRequest){
        return authService.authentication(authRequest);
    }
}
