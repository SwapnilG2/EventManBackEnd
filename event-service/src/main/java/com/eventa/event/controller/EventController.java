package com.eventa.event.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventa.event.dto.EventResponse;
import com.eventa.event.entity.Event;
import com.eventa.event.service.EventService;

@RestController
@RequestMapping("/events")
public class EventController {

	@Autowired
	private EventService eventService;

	@PostMapping
	public ResponseEntity<Event> createEvent(@RequestBody Event event) {
		Event saved = eventService.createEvent(event);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EventResponse> getEventById(@PathVariable Long id) {
		Event event = eventService.getEventById(id);
	    EventResponse response = new EventResponse(event.getId(), event.getEventName());
	    return ResponseEntity.ok(response);
	}

	@GetMapping
	public List<Event> getAllEvents() {
		return eventService.getAllEvents();
	}

	@DeleteMapping("/{id}")
	public String deleteEvent(@PathVariable Long id) {
		eventService.deleteEvent(id);
		return "Event deleted successfully.";
	}
}
