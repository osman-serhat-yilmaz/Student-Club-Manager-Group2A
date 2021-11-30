package com.app.repository;

import com.app.event_management.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@EnableJpaRepositories
public interface EventRepository extends JpaRepository<Event, UUID> {
    public List<Event> findEventsByName(String name);
    public Event getById(UUID id);
}
