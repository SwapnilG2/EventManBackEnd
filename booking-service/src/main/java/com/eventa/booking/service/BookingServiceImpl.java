package com.eventa.booking.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventa.booking.client.EventClient;
import com.eventa.booking.client.NotificationClient;
import com.eventa.booking.client.UserClient;
import com.eventa.booking.dto.EventResponse;
import com.eventa.booking.dto.NotificationRequest;
import com.eventa.booking.dto.UserResponse;
import com.eventa.booking.entity.Booking;
import com.eventa.booking.entity.BookingStatus;
import com.eventa.booking.repository.BookingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

	@Autowired
	private UserClient userClient;

	@Autowired
	private EventClient eventClient;

	@Autowired
	private NotificationClient notificationClient;

	private final BookingRepository bookingRepository;

	@Override
	public Booking createBooking(Booking booking) {
		booking.setStatus(BookingStatus.CONFIRMED);
		booking.setBookingDate(LocalDateTime.now());

		Booking savedBooking = bookingRepository.save(booking);
		UserResponse user = userClient.getUserById(savedBooking.getUserId());
		EventResponse event = eventClient.getEventById(savedBooking.getEventId());

		// Send notification
		notificationClient.sendNotification(new NotificationRequest(user.getEmail(), "Booking Confirmed",
				"Your booking for " + event.getEventName() + " is confirmed on " + savedBooking.getBookingDate()));

		return savedBooking;
	}

	@Override
	public Booking getBookingById(Long id) {
		return bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
	}

	@Override
	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}

	@Override
	public void cancelBooking(Long id) {
		Booking booking = getBookingById(id);
		booking.setStatus(BookingStatus.CANCELLED);
		bookingRepository.save(booking);
	}

}
