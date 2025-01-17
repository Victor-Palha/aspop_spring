package com.victorpalha.aspop_spring.domain.event.dtos;

import com.victorpalha.aspop_spring.domain.event.entity.EventEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventRequestDTO {
    private String title;
    private String description;
    @URL
    private String eventUrl;
    private String startInscription;
    private String endInscription;
    private String initialDateEvent;
    private String finalDateEvent;
    @URL
    private String eventBannerUrl;

    public EventEntity toEntity() {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setTitle(title);
        eventEntity.setDescription(description);
        eventEntity.setEventUrl(eventUrl);
        eventEntity.setStartInscription(startInscription);
        eventEntity.setEndInscription(endInscription);
        eventEntity.setInitialDateEvent(initialDateEvent);
        eventEntity.setFinalDateEvent(finalDateEvent);
        eventEntity.setEventBannerUrl(eventBannerUrl);
        return eventEntity;
    }
}
