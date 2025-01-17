package com.victorpalha.aspop_spring.domain.event.useCases;

import com.victorpalha.aspop_spring.domain.event.dtos.CreateEventRequestDTO;
import com.victorpalha.aspop_spring.domain.event.entity.EventEntity;
import com.victorpalha.aspop_spring.domain.event.repository.EventsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateEventUseCaseTest {
    @InjectMocks
    private CreateEventUseCase createEventUseCase;

    @Mock
    private EventsRepository eventsRepository;

    @Test
    public void should_create_event() {
        CreateEventRequestDTO createEventRequestDTO = CreateEventRequestDTO
                .builder()
                .title("title")
                .description("description")
                .eventUrl("https://example.com")
                .startInscription("2025-01-15 00:00:01")
                .endInscription("2025-01-17 00:00:00")
                .initialDateEvent("2025-01-23 07:00:00")
                .finalDateEvent("2025-01-25 19:00:00")
                .build();

        when(eventsRepository.save(createEventRequestDTO.toEntity())).thenReturn(createEventRequestDTO.toEntity());

        EventEntity result = createEventUseCase.execute(createEventRequestDTO);
        assert (result != null);
        assert (result.getTitle().equals("title"));
        assert (result.getDescription().equals("description"));
        assert (result.getEventUrl().equals("https://example.com"));
    }
}
