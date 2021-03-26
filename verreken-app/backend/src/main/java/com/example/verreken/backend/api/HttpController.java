package com.example.verreken.backend.api;

import com.example.verreken.backend.model.receive.Payment;
import com.example.verreken.backend.model.send.Settlement;
import com.example.verreken.backend.services.DatabaseService;
import com.example.verreken.backend.services.SettlementService;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class HttpController {

    private final DatabaseService databaseService;
    private final SettlementService settlementService;
    private final Gson gson;

    public HttpController(DatabaseService databaseService, SettlementService settlementService) {
        this.databaseService = databaseService;
        this.settlementService = settlementService;
        this.gson = new Gson();
    }

    @PostMapping("payment")
    public void postPayments(@RequestBody List<Payment> payments) {
        Settlement settlement = this.settlementService.settle(payments);
        this.databaseService.saveSettlement(settlement);
    }

    @GetMapping("history")
    public List<Settlement> getHistory() {
        return this.databaseService.getHistory();
    }

}
