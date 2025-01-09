package com.victorpalha.aspop_spring.domain.news.useCases;

import com.victorpalha.aspop_spring.domain.news.entities.NewsEntity;
import com.victorpalha.aspop_spring.domain.news.repository.NewsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SearchNewsUseCaseTest {

    @InjectMocks
    private SearchNewsUseCase searchNewsUseCase;

    @Mock
    private NewsRepository newsRepository;

    @Test
    public void should_return_all_news_when_keyword_is_null() {
        // Mocking
        NewsEntity news1 = new NewsEntity();
        news1.setTitle("First News");
        NewsEntity news2 = new NewsEntity();
        news2.setTitle("Second News");
        when(newsRepository.findAll()).thenReturn(Arrays.asList(news1, news2));

        // Execution
        List<NewsEntity> result = searchNewsUseCase.execute(null);

        // Assertions
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(newsRepository, times(1)).findAll();
        verify(newsRepository, never()).findByTitleOrDescriptionLike(any());
    }

    @Test
    public void should_return_all_news_when_keyword_is_empty() {
        // Mocking
        NewsEntity news1 = new NewsEntity();
        news1.setTitle("First News");
        NewsEntity news2 = new NewsEntity();
        news2.setTitle("Second News");
        when(newsRepository.findAll()).thenReturn(Arrays.asList(news1, news2));

        // Execution
        List<NewsEntity> result = searchNewsUseCase.execute("");

        // Assertions
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(newsRepository, times(1)).findAll();
        verify(newsRepository, never()).findByTitleOrDescriptionLike(any());
    }

    @Test
    public void should_return_filtered_news_by_keyword() {
        // Mocking
        String keyword = "exciting";
        NewsEntity news1 = new NewsEntity();
        news1.setTitle("Exciting News!");
        NewsEntity news2 = new NewsEntity();
        news2.setDescription("This is an exciting event.");
        NewsEntity news3 = new NewsEntity();
        news3.setTitle("Amazing News!");
        when(newsRepository.findByTitleOrDescriptionLike(keyword))
                .thenReturn(Arrays.asList(news1, news2));

        // Execution
        List<NewsEntity> result = searchNewsUseCase.execute(keyword);

        // Assertions
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(newsRepository, times(1)).findByTitleOrDescriptionLike(keyword);
        verify(newsRepository, never()).findAll();
    }

    @Test
    public void should_return_empty_list_when_no_news_matches_keyword() {
        // Mocking
        String keyword = "nonexistent";
        when(newsRepository.findByTitleOrDescriptionLike(keyword))
                .thenReturn(Collections.emptyList());

        // Execution
        List<NewsEntity> result = searchNewsUseCase.execute(keyword);

        // Assertions
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(newsRepository, times(1)).findByTitleOrDescriptionLike(keyword);
        verify(newsRepository, never()).findAll();
    }
}
