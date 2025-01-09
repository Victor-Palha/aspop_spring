package com.victorpalha.aspop_spring.domain.news.useCases;

import com.victorpalha.aspop_spring.domain.news.dtos.CreateNewsRequestDTO;
import com.victorpalha.aspop_spring.domain.news.entities.NewsEntity;
import com.victorpalha.aspop_spring.domain.news.exceptions.NewsNotFoundError;
import com.victorpalha.aspop_spring.domain.news.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This use case updates the news
 * @author Victor Palha
 * @version 1.0
 * @since 09/01/25
 */
@Service
public class ChangeNewsUseCase {
    private final NewsRepository newsRepository;
    public ChangeNewsUseCase(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public NewsEntity execute(CreateNewsRequestDTO updatedNewsDTO, String newsId){
        Optional<NewsEntity> newsExists = newsRepository.findById(newsId);
        if (newsExists.isEmpty()) {
            throw new NewsNotFoundError();
        }

        NewsEntity updatedNews = this.updateNewsEntity(newsExists.get(), updatedNewsDTO);
        newsRepository.save(updatedNews);
        return updatedNews;
    }

    private NewsEntity updateNewsEntity(NewsEntity newsToBeUpdated, CreateNewsRequestDTO newsWithNewInformation) {
        newsToBeUpdated.setTitle(
                newsWithNewInformation.getTitle() != null ? newsWithNewInformation.getTitle() : newsToBeUpdated.getTitle()
        );
        newsToBeUpdated.setDescription(
                newsWithNewInformation.getDescription() != null ? newsWithNewInformation.getDescription() : newsToBeUpdated.getDescription()
        );
        newsToBeUpdated.setSlug(
                newsWithNewInformation.getSlug() != null ? newsWithNewInformation.getSlug() : newsToBeUpdated.getSlug()
        );
        newsToBeUpdated.setBannerUrl(
                newsWithNewInformation.getBannerUrl() != null ? newsWithNewInformation.getBannerUrl() : newsToBeUpdated.getBannerUrl()
        );
        newsToBeUpdated.setContent(
                newsWithNewInformation.getContent() != null ? newsWithNewInformation.getContent() : newsToBeUpdated.getContent()
        );
        newsToBeUpdated.setAuthor(
                newsWithNewInformation.getAuthor() != null ? newsWithNewInformation.getAuthor() : newsToBeUpdated.getAuthor()
        );

        return newsToBeUpdated;
    }
}
