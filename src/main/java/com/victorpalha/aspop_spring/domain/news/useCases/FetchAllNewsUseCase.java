package com.victorpalha.aspop_spring.domain.news.useCases;

import com.victorpalha.aspop_spring.domain.news.entities.NewsEntity;
import com.victorpalha.aspop_spring.domain.news.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Return all news from the database
 * @author Victor Palha
 * @version 1.0
 * @since 09/01/25
 */
@Service
public class FetchAllNewsUseCase {
    private final NewsRepository newsRepository;
    public FetchAllNewsUseCase(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<NewsEntity> execute() {
        return newsRepository.findAll();
    }
}
