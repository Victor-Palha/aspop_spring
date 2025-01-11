package com.victorpalha.aspop_spring.domain.article.dtos;

import com.victorpalha.aspop_spring.domain.article.entity.ArticleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateArticleRequestDTO {
    private String title;
    private String description;
    @URL
    private String articleUrl;
    private String author;

    public ArticleEntity toEntity() {
        return ArticleEntity
                .builder()
                .title(title)
                .description(description)
                .articleUrl(articleUrl)
                .author(author)
                .build();
    }
}
