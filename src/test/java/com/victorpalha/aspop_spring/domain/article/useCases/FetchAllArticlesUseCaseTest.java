package com.victorpalha.aspop_spring.domain.article.useCases;

import com.victorpalha.aspop_spring.domain.article.entity.ArticleEntity;
import com.victorpalha.aspop_spring.domain.article.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FetchAllArticlesUseCaseTest {
    @InjectMocks
    private FetchAllArticlesUseCase fetchAllArticlesUseCase;

    @Mock
    private ArticleRepository articleRepository;

    @Test
    public void should_fetch_all_articles() {
        ArticleEntity article_1 = ArticleEntity.builder().articleId("1").build();
        ArticleEntity article_2 = ArticleEntity.builder().articleId("2").build();
        ArticleEntity article_3 = ArticleEntity.builder().articleId("3").build();

        List<ArticleEntity> expectedArticles = Arrays.asList(article_1, article_2, article_3);
        when(articleRepository.findAll()).thenReturn(expectedArticles);

        List<ArticleEntity> actualArticles = fetchAllArticlesUseCase.execute();
        assert actualArticles.size() == expectedArticles.size();
    }
}
