package com.sohotech.betengine.repository;

import com.sohotech.betengine.model.entity.MarketEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketRepository extends MongoRepository<MarketEntity, Long> {
}
