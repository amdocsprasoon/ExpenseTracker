package org.sebprojects.expensetracker.kafka.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class KafkaController {
    @Autowired
    private MessageProducer messageProducer;

    @GetMapping("/test-kafka")
    public String sendMessage(@RequestParam String message) {
        messageProducer.sendMessage("my-topic", message);
        return "Message sent: " + message;
    }
}
