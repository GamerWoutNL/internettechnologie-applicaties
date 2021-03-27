package com.example.verreken.backend.services;

import com.example.verreken.backend.model.receive.Payment;
import com.example.verreken.backend.model.send.Owe;
import com.example.verreken.backend.model.send.Settlement;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class SettlementService {

    public Settlement settle(List<Payment> payments) {
        Settlement settlement = new Settlement();
        List<Owe> owes = new ArrayList<>();

        Map<String, Double> balance = new HashMap<>();

        double totalExpenses = 0.0;
        for (Payment payment : payments) {
            totalExpenses += payment.getAmount();
            try {
                balance.put(payment.getName(), balance.get(payment.getName()) + payment.getAmount());
            } catch (Exception e) {
                balance.put(payment.getName(), payment.getAmount());
            }

        }

        int persons = balance.keySet().size();
        double averageCosts = totalExpenses / persons;

        for (String name : balance.keySet()) {
            balance.put(name, balance.get(name) - averageCosts);
        }

        List<Double> values = new ArrayList<>(balance.values());
        Collections.sort(values);

        while (Collections.frequency(values, 0.0) != values.size()) {
            String from = getKey(balance, values.get(0));
            String to = getKey(balance, values.get(persons - 1));
            double amount = -values.get(0);

            if (amount <= 0.01) {
                break;
            }

            balance.put(to, balance.get(to) - amount);
            balance.put(from, 0.0);

            owes.add(Owe.builder()
                    .from(from)
                    .to(to)
                    .amount(Math.round(amount * 100.0) / 100.0)
                    .build());

            values = new ArrayList<>(balance.values());
            Collections.sort(values);
        }

        settlement.setOwes(owes);
        settlement.setDate(LocalDate.now());

        return settlement;
    }

    public static <K, V> K getKey(Map<K, V> map, V value) {
        for (K key : map.keySet()) {
            if (value.equals(map.get(key))) {
                return key;
            }
        }
        return null;
    }
}
