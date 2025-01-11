package com.victorpalha.aspop_spring.domain.events.entity;

import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

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
