package org.sebprojects.expensetracker.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {
    // This class is responsible for producing messages to Kafka topics.
    // It will use the KafkaTemplate to send messages to the specified topics.

    // Add your KafkaTemplate and other dependencies here

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    // Example method to send a message
    public void sendMessage(String topic, String message) {
        // Use KafkaTemplate to send the message to the specified topic
         kafkaTemplate.send(topic, message);
    }

    // Add more methods as needed for different use cases
}
