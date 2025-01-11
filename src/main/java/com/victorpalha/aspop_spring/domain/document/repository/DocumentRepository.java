package com.victorpalha.aspop_spring.domain.document.repository;

import com.victorpalha.aspop_spring.domain.document.entity.DocumentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DocumentRepository extends MongoRepository<DocumentEntity, String> {
    List<DocumentEntity> findByPrivate(boolean aPrivate);
}
