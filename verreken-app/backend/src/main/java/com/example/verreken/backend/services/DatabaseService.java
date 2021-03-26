package com.example.verreken.backend.services;

import com.example.verreken.backend.model.send.Settlement;
import com.example.verreken.backend.repositories.SettlementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseService {

    private final SettlementRepository settlementRepository;
    private final NextSequenceService nextSequenceService;

    public DatabaseService(SettlementRepository settlementRepository, NextSequenceService nextSequenceService) {
        this.settlementRepository = settlementRepository;
        this.nextSequenceService = nextSequenceService;
    }

    public void saveSettlement(Settlement settlement) {
        settlement.setId(this.nextSequenceService.getNextSequence("settlement"));
        this.settlementRepository.save(settlement);
    }

    public List<Settlement> getHistory() {
        return this.settlementRepository.findAll();
    }
}
