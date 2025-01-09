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

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetNewsByIdUseCaseTest {
    @InjectMocks
    private GetNewsByIdUseCase getNewsByIdUseCase;

    @Mock
    private NewsRepository newsRepository;

    @Test
    public void should_get_news_by_id() {
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

        NewsEntity result = getNewsByIdUseCase.execute(newsId);
        assert expectedNewsEntity.equals(result);
        assert result.getNewsId().equals(newsId);
    }

    @Test
    public void should_not_get_news_by_id() {
        String newsId = "123456";

        when(newsRepository.findById(newsId)).thenReturn(Optional.empty());

        try{
            getNewsByIdUseCase.execute(newsId);
        } catch (Exception e) {
            assert e instanceof NewsNotFoundError;
        }
    }
}
