package com.victorpalha.aspop_spring.domain.news.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "news")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsEntity {
    @Id
    private String newsId;
    @Length(min = 1, max = 255)
    private String title;
    @Length(min = 1)
    private String content;
    @Length(min = 1, max = 350)
    private String description;
    private String slug;
    @URL
    private String bannerUrl;
    @Length(min = 1, max = 255)
    private String author;
    @CreatedDate
    private LocalDate publishedDate;

    public void setSlug(String titleToSlug) {
        if (titleToSlug == null || titleToSlug.isEmpty()) {
            this.slug = "";
        }

        String cleanedTitle = titleToSlug.toLowerCase().replaceAll("[^\\w\\s]", "");

        this.slug = cleanedTitle.replaceAll("\\s+", "-");
    }
}
