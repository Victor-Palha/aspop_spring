package com.victorpalha.aspop_spring.domain.article.entity;

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
@Document(collection = "articles")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleEntity {
    @Id
    private String articleId;
    private String title;
    private String description;
    @URL
    private String articleUrl;
    private String author;
    @CreatedDate
    private LocalDateTime createdAt;
}
