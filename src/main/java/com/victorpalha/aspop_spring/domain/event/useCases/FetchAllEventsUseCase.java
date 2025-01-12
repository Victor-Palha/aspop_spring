package com.victorpalha.aspop_spring.domain.event.useCases;

import com.victorpalha.aspop_spring.domain.event.entity.EventEntity;
import com.victorpalha.aspop_spring.domain.event.repository.EventsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author Victor Palha
 * @version 1.0
 * @since 11/01/25
 */
@Service
public class FetchAllEventsUseCase {
    private final EventsRepository eventsRepository;

    public FetchAllEventsUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    public List<EventEntity> execute() {
        return eventsRepository.findAll();
    }
}
