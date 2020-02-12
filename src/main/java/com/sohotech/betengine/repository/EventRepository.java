package com.sohotech.betengine.repository;

import com.sohotech.betengine.model.entity.EventEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends MongoRepository<EventEntity, Long> {
}
