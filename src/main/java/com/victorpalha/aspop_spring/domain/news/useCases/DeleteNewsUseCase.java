package com.victorpalha.aspop_spring.domain.news.useCases;

import com.victorpalha.aspop_spring.domain.news.entities.NewsEntity;
import com.victorpalha.aspop_spring.domain.news.exceptions.NewsNotFoundError;
import com.victorpalha.aspop_spring.domain.news.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This use case will delete one news from the database without interfering
 * On others parts
 * @author Victor Palha
 * @version 1.0
 * @since 09/01/25
 */
@Service
public class DeleteNewsUseCase {

    private final NewsRepository newsRepository;
    public DeleteNewsUseCase(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public NewsEntity execute(String newsId) {
        Optional<NewsEntity> newsExists = newsRepository.findById(newsId);
        if (newsExists.isEmpty()) {
            throw new NewsNotFoundError();
        }
        newsRepository.deleteById(newsId);
        return newsExists.get();
    }
}
