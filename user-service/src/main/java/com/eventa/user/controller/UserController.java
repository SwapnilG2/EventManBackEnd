package com.eventa.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

@CrossOrigin(origins = "http://localhost:4200")
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
	public ResponseEntity<Map<String, String>> register(@RequestBody UserRegisterRequest request) {

		Map<String, String> response = new HashMap<>();
		response.put("message", userService.registerUser(request));
		return ResponseEntity.ok(response);
	}

	@PreAuthorize("hasAnyRole('ADMIN','ORGANIZER')")
	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public Optional<User> deleteUser(@PathVariable Long id) {
		return userService.deleteUser(id);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
		User user = userService.getUserById(id);
		UserResponse response = new UserResponse(user.getId(), user.getFullName(), user.getEmail());
		return ResponseEntity.ok(response);
	}

}
