package com.victorpalha.aspop_spring.domain.news.useCases;

import com.victorpalha.aspop_spring.domain.news.dtos.CreateNewsRequestDTO;
import com.victorpalha.aspop_spring.domain.news.entities.NewsEntity;
import com.victorpalha.aspop_spring.domain.news.repository.NewsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CreateNewsUseCaseTest {

    @InjectMocks
    private CreateNewsUseCase createNewsUseCase;

    @Mock
    private NewsRepository newsRepository;

    @Test
    public void should_create_news_successfully() {
        // Arrange
        CreateNewsRequestDTO requestDTO = CreateNewsRequestDTO.builder()
                .title("Exciting News Title")
                .content("This is the content of the news.")
                .description("A brief description of the news.")
                .slug("Exciting News Title")
                .bannerUrl("https://example.com/banner.jpg")
                .author("John Doe")
                .build();

        NewsEntity expectedNewsEntity = requestDTO.toEntity();

        when(newsRepository.save(any(NewsEntity.class))).thenReturn(expectedNewsEntity);

        // Act
        NewsEntity result = createNewsUseCase.execute(requestDTO);
        // Assert
        assertNotNull(result);
        assertEquals(expectedNewsEntity.getTitle(), result.getTitle());
        assertEquals(expectedNewsEntity.getContent(), result.getContent());
        assertEquals(expectedNewsEntity.getDescription(), result.getDescription());
        assertEquals("exciting-news-title", expectedNewsEntity.getSlug());
        assertEquals(expectedNewsEntity.getBannerUrl(), result.getBannerUrl());
        assertEquals(expectedNewsEntity.getAuthor(), result.getAuthor());

        verify(newsRepository, times(1)).save(any(NewsEntity.class));
    }

    @Test
    public void should_throw_exception_when_request_is_null() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> createNewsUseCase.execute(null));

        verify(newsRepository, never()).save(any(NewsEntity.class));
    }
}
