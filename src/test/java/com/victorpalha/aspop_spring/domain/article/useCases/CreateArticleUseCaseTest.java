package com.victorpalha.aspop_spring.domain.article.useCases;

import com.victorpalha.aspop_spring.domain.article.dtos.CreateArticleRequestDTO;
import com.victorpalha.aspop_spring.domain.article.entity.ArticleEntity;
import com.victorpalha.aspop_spring.domain.article.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateArticleUseCaseTest {
    @InjectMocks
    private CreateArticleUseCase createArticleUseCase;

    @Mock
    private ArticleRepository articleRepository;

    @Test
    public void should_create_article() {
        CreateArticleRequestDTO createArticleRequestDTO = CreateArticleRequestDTO
                .builder()
                .articleUrl("http://example.com/article")
                .author("John Doe")
                .title("Title")
                .description("Description")
                .build();

        when(articleRepository.save(any(ArticleEntity.class))).thenReturn(createArticleRequestDTO.toEntity());

        ArticleEntity articleEntity = createArticleUseCase.execute(createArticleRequestDTO);
        assert articleEntity != null;
        assert articleEntity.getTitle().equals("Title");
        assert articleEntity.getDescription().equals("Description");
        assert articleEntity.getAuthor().equals("John Doe");
        assert articleEntity.getArticleUrl().equals("http://example.com/article");

    }
}
