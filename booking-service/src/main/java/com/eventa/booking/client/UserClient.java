package com.eventa.booking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.eventa.booking.dto.UserResponse;

@FeignClient(name = "user-service")
public interface UserClient {

	@GetMapping("/users/{id}")
    UserResponse getUserById(@PathVariable Long id);
}
