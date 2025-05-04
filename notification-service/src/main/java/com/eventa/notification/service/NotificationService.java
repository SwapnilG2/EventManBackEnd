package com.eventa.notification.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eventa.notification.entity.Notification;
import com.eventa.notification.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {
	
	private final NotificationRepository notificationRepository;

	public Notification sendNotification(String to, String subject, String message) {
		Notification n = Notification.builder()
	            .recipientEmail(to)
	            .subject(subject)
	            .message(message)
	            .createdAt(new Date())
	            .status("SENT")
	            .build();
	        return notificationRepository.save(n);
	}

	public List<Notification> getAll(){
		 return notificationRepository.findAll();
	}

	public Optional<Notification> getById(Long id){
		return notificationRepository.findById(id);
	}
}
