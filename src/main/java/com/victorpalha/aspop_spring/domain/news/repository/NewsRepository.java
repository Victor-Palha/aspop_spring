package com.victorpalha.aspop_spring.domain.news.repository;

import com.victorpalha.aspop_spring.domain.news.entities.NewsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface NewsRepository extends MongoRepository<NewsEntity, String> {
    @Query("{ $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'description': { $regex: ?0, $options: 'i' } } ] }")
    List<NewsEntity> findByTitleOrDescriptionLike(String keyword);
}
