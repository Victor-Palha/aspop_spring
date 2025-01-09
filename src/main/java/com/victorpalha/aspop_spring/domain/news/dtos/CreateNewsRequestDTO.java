package com.victorpalha.aspop_spring.domain.news.dtos;

import com.victorpalha.aspop_spring.domain.news.entities.NewsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateNewsRequestDTO {
    private String title;
    private String content;
    private String description;
    private String slug;
    private String bannerUrl;
    private String author;

    public NewsEntity toEntity() {
        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setTitle(title);
        newsEntity.setContent(content);
        newsEntity.setDescription(description);
        newsEntity.setSlug(slug);
        newsEntity.setAuthor(author);
        newsEntity.setBannerUrl(bannerUrl);

        return newsEntity;
    }
}
