package com.example.verreken.backend.api;

import com.example.verreken.backend.model.receive.Payment;
import com.example.verreken.backend.model.send.Settlement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class HttpController {

    @PostMapping("payment")
    public void postPayments(@RequestBody List<Payment> payments) {
        System.out.println(payments);
    }

    @GetMapping("history")
    public List<Settlement> getHistory() {
        return null;
    }

}
