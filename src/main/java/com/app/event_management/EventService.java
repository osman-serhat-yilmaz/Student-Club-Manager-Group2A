package com.app.event_management;

import com.app.club_management.Club;
import com.app.repository.ClubRepository;
import com.app.repository.EventRepository;
import com.app.repository.ParticipationFormRepository;
import com.app.repository.TagRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired,@NonNull}))
public class EventService {
    private final EventRepository eventRepository;
    private final TagRepository tagRepository;
    private final ParticipationFormRepository participationFormRepository;

    //event service
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public void delete(UUID id) {
        eventRepository.deleteById(id);
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event findOneById( UUID id) {
        return eventRepository.getById(id);
    }

    public List<Event> findEventByName(String name) {
        return eventRepository.findEventsByName(name);
    }

    public Long count() {
        return eventRepository.count();
    }


}
