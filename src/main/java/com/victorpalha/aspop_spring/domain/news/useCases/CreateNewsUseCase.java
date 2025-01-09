package com.victorpalha.aspop_spring.domain.news.useCases;

import com.victorpalha.aspop_spring.domain.news.dtos.CreateNewsRequestDTO;
import com.victorpalha.aspop_spring.domain.news.entities.NewsEntity;
import com.victorpalha.aspop_spring.domain.news.repository.NewsRepository;
import org.springframework.stereotype.Service;

/**
 * This use case create a news base on CreateNewsRequestDTO
 * @author Victor Palha
 * @version 1.0
 * @since 08/01/25
 */
@Service
public class CreateNewsUseCase {
    private final NewsRepository newsRepository;

    public CreateNewsUseCase(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public NewsEntity execute(CreateNewsRequestDTO createNewsRequestDTO) {
        return newsRepository.save(createNewsRequestDTO.toEntity());
    }
}
