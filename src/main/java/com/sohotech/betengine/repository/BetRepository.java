package com.sohotech.betengine.repository;

import com.sohotech.betengine.model.entity.BetEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetRepository extends MongoRepository<BetEntity, Long> {
}
