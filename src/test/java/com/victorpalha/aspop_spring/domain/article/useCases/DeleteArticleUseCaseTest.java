package com.victorpalha.aspop_spring.domain.article.useCases;

import com.victorpalha.aspop_spring.domain.article.entity.ArticleEntity;
import com.victorpalha.aspop_spring.domain.article.exceptions.ArticleNotFoundError;
import com.victorpalha.aspop_spring.domain.article.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteArticleUseCaseTest {
    @InjectMocks
    private DeleteArticleUseCase deleteArticleUseCase;

    @Mock
    private ArticleRepository articleRepository;

    @Test
    public void should_delete_article() {
        String articleId = "1234";
        ArticleEntity articleToBeDeleted = ArticleEntity
                .builder()
                .articleId(articleId)
                .articleUrl("http://example.com/article")
                .author("John Doe")
                .title("Title")
                .description("Description")
                .build();
        when(articleRepository.findById(articleId)).thenReturn(Optional.of(articleToBeDeleted));

        ArticleEntity deletedArticle = deleteArticleUseCase.execute(articleId);
        assert deletedArticle != null;
        assert deletedArticle.getArticleId().equals(articleId);
        assert deletedArticle.getArticleUrl().equals("http://example.com/article");
        assert deletedArticle.getAuthor().equals("John Doe");
        assert deletedArticle.getTitle().equals("Title");
        assert deletedArticle.getDescription().equals("Description");
    }

    @Test
    public void should_throw_exception_when_article_not_found() {
        String articleId = "1234";
        when(articleRepository.findById(articleId)).thenReturn(Optional.empty());

        try{
            deleteArticleUseCase.execute(articleId);
        } catch (Exception e) {
            assert e instanceof ArticleNotFoundError;
        }
    }
}
