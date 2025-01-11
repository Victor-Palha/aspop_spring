package com.victorpalha.aspop_spring.domain.event.useCases;

import com.victorpalha.aspop_spring.domain.event.dtos.CreateEventRequestDTO;
import com.victorpalha.aspop_spring.domain.event.entity.EventEntity;
import com.victorpalha.aspop_spring.domain.event.repository.EventsRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateEventUseCase {
    private final EventsRepository eventsRepository;
    public CreateEventUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    public EventEntity execute(CreateEventRequestDTO createEventRequestDTO){
        return eventsRepository.save(createEventRequestDTO.toEntity());
    }
}
