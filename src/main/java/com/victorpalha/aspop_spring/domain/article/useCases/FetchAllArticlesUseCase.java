package com.victorpalha.aspop_spring.domain.article.useCases;

import com.victorpalha.aspop_spring.domain.article.entity.ArticleEntity;
import com.victorpalha.aspop_spring.domain.article.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FetchAllArticlesUseCase {
    private final ArticleRepository articleRepository;

    public FetchAllArticlesUseCase(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<ArticleEntity> execute() {
        return articleRepository.findAll();
    }
}
