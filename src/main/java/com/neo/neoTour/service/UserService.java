package com.neo.neoTour.service;

import com.neo.neoTour.dto.JwtRequestDto;
import com.neo.neoTour.dto.UserResponseDto;
import com.neo.neoTour.entity.Role;
import com.neo.neoTour.entity.User;
import com.neo.neoTour.exception.NotFoundException;
import com.neo.neoTour.mapper.UserMapper;
import com.neo.neoTour.repository.RoleRepository;
import com.neo.neoTour.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public List<UserResponseDto> getAllUsers(){
        List<User> allUsers = userRepository.findAll();
        return userMapper.convertToDtoList(allUsers);
    }

    public void deleteUserById(Long id){
        User user = userRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("User with id: " + id + " not found."));
        user.getRoles().clear();
        userRepository.save(user);
        userRepository.deleteById(id);
    }

    @Transactional
    public User registerNewUser(String phoneNumber){
        User user = new User();
        user.setPhoneNumber(phoneNumber);
        user.setAdmin(false);
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new NotFoundException("Role not found."));
        user.addRole(userRole);
        return userRepository.save(user);
    }

    public User findByPhoneNumber(String phoneNumber){
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new NotFoundException("User not found."));
    }

    public void createAdmin( JwtRequestDto jwtRequestDto) {
        if(userRepository.existsByPhoneNumber(jwtRequestDto.getPhoneNumber())){
            Optional<User> user = userRepository.findByPhoneNumber(jwtRequestDto.getPhoneNumber());
        }
        User user = new User();
        user.setPhoneNumber(jwtRequestDto.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(jwtRequestDto.getPassword()));
        user.setAdmin(true);

        Role userRole = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("User Role not found"));
        Role adminRole = roleRepository.findByName("ROLE_ADMIN").orElseThrow(() -> new RuntimeException("Admin Role not found"));

        user.getRoles().add(userRole);
        user.getRoles().add(adminRole);

        User savedUser = userRepository.save(user);

        //return userMapper.convertToDto(savedUser);
    }
}
