package com.eventa.booking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.eventa.booking.dto.NotificationRequest;

@FeignClient(name = "notification-service")
public interface NotificationClient {
	
	@PostMapping("/notifications")
    void sendNotification(@RequestBody NotificationRequest request);

}
