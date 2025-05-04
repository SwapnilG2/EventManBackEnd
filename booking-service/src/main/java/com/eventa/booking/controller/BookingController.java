package com.eventa.booking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventa.booking.entity.Booking;
import com.eventa.booking.entity.BookingStatus;
import com.eventa.booking.service.BookingService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {
	
	private final BookingService bookingService;

	@PreAuthorize("hasRole('ATTENDEE')")
	@PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
		if (booking.getStatus() == null) {
	        booking.setStatus(BookingStatus.PENDING);
	    }
		Booking saved = bookingService.createBooking(booking);
	    return new ResponseEntity<Booking>(saved, HttpStatus.CREATED);
    }

	@PreAuthorize("hasAnyRole('ATTENDEE','ORGANIZER')")
    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @PreAuthorize("hasAnyRole('ATTENDEE','ADMIN')")
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @PreAuthorize("hasRole('ATTENDEE','ADMIN')")
    @DeleteMapping("/{id}")
    public String cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return "Booking cancelled successfully.";
    }


}
