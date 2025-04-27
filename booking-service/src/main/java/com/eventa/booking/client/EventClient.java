package com.eventa.booking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.eventa.booking.dto.EventResponse;

@FeignClient(name = "event-service")
public interface EventClient {

	@GetMapping("/events/{id}")
	EventResponse getEventById(@PathVariable Long id);
}
