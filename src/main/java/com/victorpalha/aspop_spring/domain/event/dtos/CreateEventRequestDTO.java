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
    private LocalDateTime startInscription;
    private LocalDateTime endInscription;
    private LocalDateTime initialDateEvent;
    private LocalDateTime finalDateEvent;
    @URL
    private String eventBannerUrl;

    public EventEntity toEntity() {
        return EventEntity.builder()
                .title(title)
                .description(description)
                .eventUrl(eventUrl)
                .startInscription(startInscription)
                .endInscription(endInscription)
                .initialDateEvent(initialDateEvent)
                .finalDateEvent(finalDateEvent)
                .eventBannerUrl(eventBannerUrl)
                .build();
    }
}
