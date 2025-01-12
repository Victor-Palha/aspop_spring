package com.victorpalha.aspop_spring.domain.event.useCases;

import com.victorpalha.aspop_spring.domain.event.entity.EventEntity;
import com.victorpalha.aspop_spring.domain.event.exceptions.EventNotFoundError;
import com.victorpalha.aspop_spring.domain.event.repository.EventsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
/**
 * @author Victor Palha
 * @version 1.0
 * @since 11/01/25
 */
@Service
public class GetEventByIdUseCase {
    private final EventsRepository eventsRepository;
    public GetEventByIdUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    public EventEntity execute(String eventId) {
        Optional<EventEntity> eventEntity = eventsRepository.findById(eventId);
        if (eventEntity.isEmpty()) {
            throw new EventNotFoundError();
        }
        return eventEntity.get();
    }
}
