package com.eventa.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventa.booking.entity.Booking;
import com.eventa.booking.entity.BookingStatus;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{
	
	List<Booking> findByUserId(Long userId);
	List<Booking> findByStatus(BookingStatus status);
}
