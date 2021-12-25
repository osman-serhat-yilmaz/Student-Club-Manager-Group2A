package com.app.repository;

import com.app.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@EnableJpaRepositories
public interface EventRepository extends JpaRepository<Event, UUID> {
    public List<Event> findEventsByName(String name);
    public List<Event> findEventsByClubIDAndDateBefore(UUID clubId, Long date);
    public List<Event> findEventsByClubIDAndDateAfter(UUID clubId, Long date);

    Event findEventById(UUID id);
}
