package com.victorpalha.aspop_spring.domain.events.repository;

import com.victorpalha.aspop_spring.domain.events.entity.EventEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventsRepository extends MongoRepository<EventEntity, String> { }
