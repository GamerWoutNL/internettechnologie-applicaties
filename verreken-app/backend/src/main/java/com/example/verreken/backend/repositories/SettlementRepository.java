package com.example.verreken.backend.repositories;

import com.example.verreken.backend.model.send.Settlement;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SettlementRepository extends MongoRepository<Settlement, Long> { }
