package com.eventa.booking.service;

import java.util.List;

import com.eventa.booking.entity.Booking;

public interface BookingService {
	Booking createBooking(Booking booking);
    Booking getBookingById(Long id);
    List<Booking> getAllBookings();
    void cancelBooking(Long id);
}
