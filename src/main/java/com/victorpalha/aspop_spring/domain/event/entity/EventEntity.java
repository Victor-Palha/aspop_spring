package com.victorpalha.aspop_spring.domain.event.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "events")
@NoArgsConstructor
@AllArgsConstructor
public class EventEntity {
    @Id
    private String eventId;
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
    @CreatedDate
    private LocalDateTime createdAt;
}
