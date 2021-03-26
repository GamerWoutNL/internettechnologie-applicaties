package com.example.verreken.backend.api;

import com.example.verreken.backend.model.receive.Payment;
import com.example.verreken.backend.model.send.Settlement;
import com.example.verreken.backend.services.SettlementService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;


@Controller
public class WebSocketController {

    private final SettlementService settlementService;
    private final Gson gson;

    public WebSocketController(SettlementService settlementService) {
        this.settlementService = settlementService;
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Payment.class, new Payment())
                .create();
    }

    @SendTo("/topic/data")
    @MessageMapping("/data")
    public String handle(String payload) {
        List<Payment> payments = this.gson.fromJson(payload, new TypeToken<ArrayList<Payment>>(){}.getType());
        Settlement settlement = this.settlementService.settle(payments);

        return this.gson.toJson(settlement);
    }

}
