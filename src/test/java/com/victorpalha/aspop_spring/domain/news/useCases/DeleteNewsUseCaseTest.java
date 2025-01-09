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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteNewsUseCaseTest {
    @InjectMocks
    private DeleteNewsUseCase deleteNewsUseCase;

    @Mock
    private NewsRepository newsRepository;

    @Test
    public void should_delete_news_if_exists() {
        String newsId = "123456";
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
        expectedNewsEntity.setNewsId(newsId);


        when(newsRepository.findById(newsId)).thenReturn(Optional.of(expectedNewsEntity));
        NewsEntity newsDeleted = deleteNewsUseCase.execute(newsId);
        assert (newsDeleted.getNewsId().equals(newsId));
        assert (newsDeleted.getTitle().equals(expectedNewsEntity.getTitle()));
    }

    @Test
    public void should_not_delete_news_if_does_not_exists() {
        String newsId = "123456";
        // Arrange

        when(newsRepository.findById(newsId)).thenReturn(Optional.empty());
        try{
            deleteNewsUseCase.execute(newsId);
        } catch (Exception e) {
            assert e instanceof NewsNotFoundError;
        }
    }
}
