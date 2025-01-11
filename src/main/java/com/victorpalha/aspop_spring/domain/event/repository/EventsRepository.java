package com.victorpalha.aspop_spring.domain.event.repository;

import com.victorpalha.aspop_spring.domain.event.entity.EventEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventsRepository extends MongoRepository<EventEntity, String> { }
