package com.victorpalha.aspop_spring.domain.news.useCases;

import com.victorpalha.aspop_spring.domain.news.entities.NewsEntity;
import com.victorpalha.aspop_spring.domain.news.exceptions.NewsNotFoundError;
import com.victorpalha.aspop_spring.domain.news.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Get unique news by his ID
 * @author Victor Palha
 * @version 1.0
 * @since 09/01/25
 */
@Service
public class GetNewsByIdUseCase {
    private final NewsRepository newsRepository;
    public GetNewsByIdUseCase(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public NewsEntity execute(String NewsId) {
        Optional<NewsEntity> newsExists = newsRepository.findById(NewsId);
        if (newsExists.isEmpty()) {
            throw new NewsNotFoundError();
        }

        return newsExists.get();
    }
}
