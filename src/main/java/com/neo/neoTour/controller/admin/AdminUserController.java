package com.neo.neoTour.controller.admin;

import com.neo.neoTour.dto.JwtRequestDto;
import com.neo.neoTour.dto.UserResponseDto;
import com.neo.neoTour.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Admin User Controller",
        description = "Private endpoints for admins to control users"
)
@RestController
@RequiredArgsConstructor
@Hidden
@RequestMapping("/admin/users")
public class AdminUserController {
    private final UserService userService;

    @Operation(
            summary = "Get all users",
            description = "Admin user can get a list of all users with details"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "403", description = "You don't have permission to access this resource")
    })
    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Operation(
            summary = "Delete user",
            description = "Admin user can delete user by id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "403", description = "You don't have permission to access this resource"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.ok("User successfully deleted.");
    }

    @Operation(
            summary = "Create user",
            description = "Admin user can create a new user"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "403", description = "You don't have permission to access this resource")
    })
    @PostMapping("/create-admin")
    public ResponseEntity<String> createAdminUser(@RequestBody JwtRequestDto jwtRequestDto) {
        userService.createAdmin(jwtRequestDto);
        return ResponseEntity.ok("Admin created successfully.");
    }
}
