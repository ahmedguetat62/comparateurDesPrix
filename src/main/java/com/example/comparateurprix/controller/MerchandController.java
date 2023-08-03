package com.example.comparateurprix.controller;

import com.example.comparateurprix.Consumer.MerchandConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MerchandController {

    private final MerchandConsumer merchandConsumer;

    @Autowired
    public MerchandController(MerchandConsumer merchandConsumer) {
        this.merchandConsumer = merchandConsumer;
    }

    @GetMapping("/consume-csv")
    public String consumeCsvAndSendToKafka() {
        String csvFilePath = "D:\\jetbrain\\comparateurprix\\src\\main\\java\\com\\example\\comparateurprix\\Consumer\\merchand.csv"; // Provide the actual path
        String kafkaTopic = "your-kafka-topic"; // Provide the actual Kafka topic

        merchandConsumer.consumeAndSendToKafka(csvFilePath, kafkaTopic);

        return "CSV data consumed and sent to Kafka.";
    }
}
