package com.victorpalha.aspop_spring.domain.news.useCases;

import com.victorpalha.aspop_spring.domain.news.dtos.CreateNewsRequestDTO;
import com.victorpalha.aspop_spring.domain.news.entities.NewsEntity;
import com.victorpalha.aspop_spring.domain.news.exceptions.NewsNotFoundError;
import com.victorpalha.aspop_spring.domain.news.repository.NewsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ChangeNewsUseCaseTest {
    @InjectMocks
    private ChangeNewsUseCase changeNewsUseCase;

    @Mock
    private NewsRepository newsRepository;

    @Test
    public void should_update_news_successfully() {
        String newsId = "123123";
        // Arrange
        CreateNewsRequestDTO requestDTO = CreateNewsRequestDTO.builder()
                .title("Exciting News Title 2")
                .content("This is the content of the news 2")
                .description("A brief description of the news 2")
                .slug("Exciting News Title 2")
                .bannerUrl("https://example.com/banner.jpg")
                .author("Jane Doe")
                .build();

        NewsEntity createdNews = NewsEntity
                .builder()
                .title("Exciting News Title")
                .content("This is the content of the news")
                .description("A brief description of the news")
                .slug("Exciting News Title")
                .bannerUrl("https://example.com/banner.jpg")
                .author("John Doe")
                .build();
        createdNews.setNewsId(newsId);
        when(newsRepository.findById(newsId)).thenReturn(Optional.of(createdNews));
        NewsEntity expectedResponse = requestDTO.toEntity();
        // Act
        NewsEntity result = changeNewsUseCase.execute(requestDTO, newsId);
        // Assert
        assertNotNull(result);
        assert result.getNewsId().equals("123123");
        assert result.getTitle().equals(expectedResponse.getTitle());
        assert result.getContent().equals(expectedResponse.getContent());
        assert result.getDescription().equals(expectedResponse.getDescription());
        assert result.getSlug().equals("exciting-news-title-2");
        assert result.getBannerUrl().equals(expectedResponse.getBannerUrl());
        assert result.getAuthor().equals(expectedResponse.getAuthor());
    }

    @Test
    public void should_not_update_news_successfully_if_news_is_not_found() {
        String newsId = "123123";
        // Arrange
        CreateNewsRequestDTO requestDTO = CreateNewsRequestDTO.builder()
                .title("Exciting News Title 2")
                .content("This is the content of the news 2")
                .description("A brief description of the news 2")
                .slug("Exciting News Title 2")
                .bannerUrl("https://example.com/banner.jpg")
                .author("Jane Doe")
                .build();
        when(newsRepository.findById(newsId)).thenReturn(Optional.empty());
        // Act
        try{
            changeNewsUseCase.execute(requestDTO, newsId);
        } catch (Exception e) {
            assert e instanceof NewsNotFoundError;
        }
    }
}
