package com.victorpalha.aspop_spring.domain.news.useCases;

import com.victorpalha.aspop_spring.domain.news.entities.NewsEntity;
import com.victorpalha.aspop_spring.domain.news.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This use case receives a keyword and search this keyword on title or description
 * And if exists news with matches, returns the list of News
 * @author Victor Palha
 * @version 1.0
 * @since 09/01/25
 */
@Service
public class SearchNewsUseCase {
    private final NewsRepository newsRepository;
    public SearchNewsUseCase(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<NewsEntity> execute(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return newsRepository.findAll();
        }
        return newsRepository.findByTitleOrDescriptionLike(keyword);
    }
}
