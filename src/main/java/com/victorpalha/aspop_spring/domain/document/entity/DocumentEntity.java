package com.victorpalha.aspop_spring.domain.document.entity;

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
@Document(collection = "documents")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentEntity {
    @Id
    private String documentId;
    private String title;
    @URL
    private String documentUrl;
    private boolean isPrivate = false;
    private String description;
    @CreatedDate
    private LocalDateTime createdAt;
}
