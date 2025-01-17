package com.victorpalha.aspop_spring.domain.event.useCases;

import com.victorpalha.aspop_spring.domain.event.dtos.CreateEventRequestDTO;
import com.victorpalha.aspop_spring.domain.event.entity.EventEntity;
import com.victorpalha.aspop_spring.domain.event.exceptions.EventNotFoundError;
import com.victorpalha.aspop_spring.domain.event.repository.EventsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteEventUseCaseTest {
    @InjectMocks
    private DeleteEventUseCase deleteEventUseCase;

    @Mock
    private EventsRepository eventsRepository;

    @Test
    public void should_delete_event() {
        String eventId = "eventId";
        EventEntity eventToBeDeleted = CreateEventRequestDTO
                .builder()
                .title("title")
                .description("description")
                .eventUrl("https://example.com")
                .startInscription("2025-01-15 00:00:01")
                .endInscription("2025-01-17 00:00:00")
                .initialDateEvent("2025-01-23 07:00:00")
                .finalDateEvent("2025-01-25 19:00:00")
                .build().toEntity();
        eventToBeDeleted.setEventId(eventId);

        when(eventsRepository.findById(eventId)).thenReturn(Optional.of(eventToBeDeleted));

        EventEntity result = deleteEventUseCase.execute(eventId);
        assert (result != null);
        assert (result.getEventId().equals(eventId));
    }

    @Test
    public void should_not_delete_event_if_not_found() {
        String eventId = "eventId";

        when(eventsRepository.findById(eventId)).thenReturn(Optional.empty());

        try{
            deleteEventUseCase.execute(eventId);
        } catch (Exception e) {
            assert e instanceof EventNotFoundError;
        }

    }
}
