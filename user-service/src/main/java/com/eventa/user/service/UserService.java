package com.eventa.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eventa.user.dto.UserRegisterRequest;
import com.eventa.user.entity.User;
import com.eventa.user.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public String registerUser(UserRegisterRequest request) {
		
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "Email already registered!";
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();


        userRepository.save(user);
       
//        response.put("message", "User registered successfully");
//        return ResponseEntity.ok(response);
        return "User registered successfully!";
    }
	
	public List<User>getAllUsers(){
		
		return userRepository.findAll();
	}
	
	public Optional<User> deleteUser(Long id) {
		userRepository.deleteById(id);
		return userRepository.findById(id);
	}
	
	public User getUserById(Long id) {
		
		return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
	}
}