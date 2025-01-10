package com.victorpalha.aspop_spring.domain.image.repository;

import com.victorpalha.aspop_spring.domain.image.entity.ImageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<ImageEntity, String> { }
