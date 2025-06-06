package com.eventa.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventa.notification.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>{

}
