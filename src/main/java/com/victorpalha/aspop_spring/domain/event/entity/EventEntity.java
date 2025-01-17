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
import java.time.format.DateTimeFormatter;

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

    public void setStartInscription(String dateString) {
        this.startInscription = this.parseStringDateEvent(dateString);
    }
    public void setEndInscription(String dateString) {
        this.endInscription = this.parseStringDateEvent(dateString);
    }
    public void setInitialDateEvent(String dateString) {
        this.initialDateEvent = this.parseStringDateEvent(dateString);
    }
    public void setFinalDateEvent(String dateString) {
        this.finalDateEvent = this.parseStringDateEvent(dateString);
    }
    private LocalDateTime parseStringDateEvent(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }

}
