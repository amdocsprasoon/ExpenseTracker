package org.sebprojects.expensetracker.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {
    @Autowired
    private MessageProducer messageProducer;

    @GetMapping("/test-kafka")
    public String sendMessage() {
        String message = "Hello, Seb-Kafka!";
        messageProducer.sendMessage("my-topic", message);
        return "Message sent: " + message;
    }
}
