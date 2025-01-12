package com.victorpalha.aspop_spring.domain.article.useCases;

import com.victorpalha.aspop_spring.domain.article.dtos.CreateArticleRequestDTO;
import com.victorpalha.aspop_spring.domain.article.entity.ArticleEntity;
import com.victorpalha.aspop_spring.domain.article.repository.ArticleRepository;
import org.springframework.stereotype.Service;
/**
 * @author Victor Palha
 * @version 1.0
 * @since 11/01/25
 */
@Service
public class CreateArticleUseCase {
    private final ArticleRepository articleRepository;
    public CreateArticleUseCase(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleEntity execute(CreateArticleRequestDTO createArticleRequestDTO) {
        return articleRepository.save(createArticleRequestDTO.toEntity());
    }
}
