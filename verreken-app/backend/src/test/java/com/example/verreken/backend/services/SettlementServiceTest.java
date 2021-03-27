package com.example.verreken.backend.services;

import com.example.verreken.backend.model.receive.Payment;
import com.example.verreken.backend.model.send.Owe;
import com.example.verreken.backend.model.send.Settlement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SettlementServiceTest {

    private SettlementService settlementService;
    private final String[] names = { "Ricky", "Wout", "Arno", "Stijn" };
    private final double[] amounts = {98.61, 8.11, 86.83, 82.36, 61.76, 27.95, 37.27, 76.73, 76.95, 46.68};

    @BeforeEach
    void setUp() {
        this.settlementService = new SettlementService();
    }

    @Test
    void settle() {
        Settlement settlement = this.settlementService.settle(this.getPayments());

        double totalExpenses = 0.0;  // 603.25
        for (int i = 0; i < this.amounts.length; i++) {
            totalExpenses += this.amounts[i];
        }

        double averageCostsPerPerson = totalExpenses / this.names.length;  // 150.81

        /*
        Expenses per person
            Stijn   159.09
            Arno    124.10
            Ricky   237.32
            Wout     82.74
         */

        /*
        Balance per person (positive is payed to much, negative is payed to little) (what someone payed - average cost)
            Stijn     8.28
            Arno    -26.71
            Ricky    86.51
            Wout    -68.07

        The one that payed the least, pays his whole debt off to the person that payed the most

        Wout -> Ricky 68.07
            Stijn     8.28
            Arno    -26.71
            Ricky    18.44
            Wout      0.00

        Arno -> Ricky 26.71
            Stijn     8.28
            Arno      0.00
            Ricky    -8,28
            Wout      0.00

        Ricky -> Stijn 8.28
            Stijn     0.00
            Arno      0.00
            Ricky     0.00
            Wout      0.00

        For n persons, n-1 pay requests
         */

        List<Owe> owes = settlement.getOwes();

        for (Owe owe : owes) {
            switch (owe.getFrom()) {
                case "Wout" -> {
                    assertEquals(68.07, owe.getAmount());
                }
                case "Arno" -> {
                    assertEquals(26.71, owe.getAmount());
                }
                case "Ricky" -> {
                    assertEquals(8.28, owe.getAmount());
                }
                case "Stijn" -> {
                    fail();
                }
            }
        }
        
    }

    List<Payment> getPayments() {
        List<Payment> payments = new ArrayList<>();

        for (int i = 0; i < this.amounts.length; i++) {
            payments.add(Payment.builder()
                    .name(this.names[i % this.names.length])
                    .amount(this.amounts[i])
                    .build());
        }

        return payments;
    }
}
