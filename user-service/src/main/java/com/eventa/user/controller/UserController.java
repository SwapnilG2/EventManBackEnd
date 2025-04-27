package com.eventa.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventa.user.dto.UserRegisterRequest;
import com.eventa.user.dto.UserResponse;
import com.eventa.user.entity.User;
import com.eventa.user.service.UserService;
import com.eventa.user.util.JwtUtil;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserRegisterRequest request) {
		String response = userService.registerUser(request);
		return ResponseEntity.ok(response);
	}

//	@PostMapping("/login")
//	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
//
//		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        String token = jwtUtil.generateToken(userDetails.getUsername());
//        return ResponseEntity.ok(new JwtResponse(token));
//
//	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
	    User user = userService.getUserById(id);
	    UserResponse response = new UserResponse(user.getId(), user.getFullName(), user.getEmail());
	    return ResponseEntity.ok(response);
	}

}
