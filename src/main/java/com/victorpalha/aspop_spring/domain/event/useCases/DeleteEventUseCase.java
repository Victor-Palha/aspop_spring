package com.victorpalha.aspop_spring.domain.event.useCases;

import com.victorpalha.aspop_spring.domain.event.entity.EventEntity;
import com.victorpalha.aspop_spring.domain.event.exceptions.EventNotFoundError;
import com.victorpalha.aspop_spring.domain.event.repository.EventsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteEventUseCase {

    private final EventsRepository eventsRepository;

    public DeleteEventUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    public EventEntity execute(String eventId) {
        Optional<EventEntity> eventEntity = eventsRepository.findById(eventId);
        if (eventEntity.isEmpty()) {
            throw new EventNotFoundError();
        }

        eventsRepository.deleteById(eventId);
        return eventEntity.get();
    }
}
