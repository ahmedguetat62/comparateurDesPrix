package com.example.comparateurprix.Consumer;

import com.example.comparateurprix.repository.MerchandRepository;
import com.example.comparateurprix.service.MerchandService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class TopicReaderToBD {
    @Autowired
    private MerchandService merchandService;

    private static final Logger LOGGER = Logger.getLogger(TopicReaderToBD.class.getName());
    @KafkaListener(topics = "your-kafka-topic")

    public void consume( ConsumerRecord<String, String> records) {
        String value = records.value();
        String[] lines = value.split("\n");

        for (String line : lines) {
            merchandService.processInsertData(line);

            // Perform your processing logic here, e.g., saving to the database
            // merchandService.saveLineToDatabase(line);
        }
    }
}
