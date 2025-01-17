package com.victorpalha.aspop_spring.domain.event.useCases;

import com.victorpalha.aspop_spring.domain.event.entity.EventEntity;
import com.victorpalha.aspop_spring.domain.event.repository.EventsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FetchAllEventsUseCaseTest {
    @InjectMocks
    private FetchAllEventsUseCase fetchAllEventsUseCase;

    @Mock
    private EventsRepository eventsRepository;

    @Test
    public void should_get_all_events() {
        EventEntity event_1 = EventEntity.builder().eventId("1").build();
        EventEntity event_2 = EventEntity.builder().eventId("2").build();
        EventEntity event_3 = EventEntity.builder().eventId("3").build();
        List<EventEntity> events = Arrays.asList(event_1, event_2, event_3);
        when(eventsRepository.findAll()).thenReturn(events);
        List<EventEntity> result = fetchAllEventsUseCase.execute();
        assert result.size() == 3;
    }
}
