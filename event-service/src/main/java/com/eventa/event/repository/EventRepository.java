package com.eventa.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventa.event.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{

}
