package com.victorpalha.aspop_spring.domain.article.repository;

import com.victorpalha.aspop_spring.domain.article.entity.ArticleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepository extends MongoRepository<ArticleEntity, String> {
}
