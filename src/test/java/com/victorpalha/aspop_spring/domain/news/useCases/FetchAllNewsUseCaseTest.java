package com.victorpalha.aspop_spring.domain.news.useCases;

import com.victorpalha.aspop_spring.domain.news.dtos.CreateNewsRequestDTO;
import com.victorpalha.aspop_spring.domain.news.entities.NewsEntity;
import com.victorpalha.aspop_spring.domain.news.repository.NewsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FetchAllNewsUseCaseTest {
    @InjectMocks
    private FetchAllNewsUseCase fetchAllNewsUseCase;

    @Mock
    private NewsRepository newsRepository;

    @Test
    public void should_return_all_news() {
        CreateNewsRequestDTO news_1 = CreateNewsRequestDTO.builder()
                .title("Exciting News Title")
                .content("This is the content of the news.")
                .description("A brief description of the news.")
                .slug("Exciting News Title")
                .bannerUrl("https://example.com/banner.jpg")
                .author("John Doe")
                .build();
        CreateNewsRequestDTO news_2 = CreateNewsRequestDTO.builder()
                .title("Exciting News Title")
                .content("This is the content of the news.")
                .description("A brief description of the news.")
                .slug("Exciting News Title")
                .bannerUrl("https://example.com/banner.jpg")
                .author("John Doe")
                .build();
        CreateNewsRequestDTO news_3 = CreateNewsRequestDTO.builder()
                .title("Exciting News Title")
                .content("This is the content of the news.")
                .description("A brief description of the news.")
                .slug("Exciting News Title")
                .bannerUrl("https://example.com/banner.jpg")
                .author("John Doe")
                .build();
        List<NewsEntity> expected_news = Arrays.asList(news_1.toEntity(), news_2.toEntity(), news_3.toEntity());

        when(newsRepository.findAll()).thenReturn(expected_news);

        List<NewsEntity> response = fetchAllNewsUseCase.execute();
        System.out.println(response);
        assert response.size() == expected_news.size();
        assert response.containsAll(expected_news);
    }

    @Test
    public void should_return_empty_response() {
        // Mock behavior
        when(newsRepository.findAll()).thenReturn(List.of());

        // Execute use case
        List<NewsEntity> response = fetchAllNewsUseCase.execute();

        // Assertions
        assert response != null;
        assert response.isEmpty();
    }
}
