package com.victorpalha.aspop_spring.domain.article.useCases;

import com.victorpalha.aspop_spring.domain.article.entity.ArticleEntity;
import com.victorpalha.aspop_spring.domain.article.exceptions.ArticleNotFoundError;
import com.victorpalha.aspop_spring.domain.article.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
/**
 * @author Victor Palha
 * @version 1.0
 * @since 11/01/25
 */
@Service
public class DeleteArticleUseCase {
    private final ArticleRepository articleRepository;
    public DeleteArticleUseCase(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleEntity execute(String articleId) {
        Optional<ArticleEntity> article = articleRepository.findById(articleId);
        if (article.isEmpty()) {
            throw new ArticleNotFoundError();
        }

        articleRepository.deleteById(articleId);
        return article.get();
    }
}
