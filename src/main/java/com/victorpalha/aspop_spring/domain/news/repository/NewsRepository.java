package com.victorpalha.aspop_spring.domain.news.repository;

import com.victorpalha.aspop_spring.domain.news.entities.NewsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewsRepository extends MongoRepository<NewsEntity, String> { }
