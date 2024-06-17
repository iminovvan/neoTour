package com.neo.neoTour.controller.admin;

import com.neo.neoTour.dto.JwtRequestDto;
import com.neo.neoTour.dto.UserResponseDto;
import com.neo.neoTour.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class AdminUserController {
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.ok("User successfully deleted.");
    }

    @PostMapping("/create-admin")
    public ResponseEntity<String> createAdminUser(@RequestBody JwtRequestDto jwtRequestDto) {
        userService.createAdmin(jwtRequestDto);
        return ResponseEntity.ok("Admin created successfully.");
    }
}
