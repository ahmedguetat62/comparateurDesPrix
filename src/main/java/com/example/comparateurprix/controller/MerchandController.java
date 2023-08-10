package com.example.comparateurprix.controller;

import com.example.comparateurprix.Consumer.MerchandConsumer;
import com.example.comparateurprix.Consumer.TopicReaderToBD;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.tags.EditorAwareTag;

@RestController
@RequestMapping("/api")
public class MerchandController {
    private final MerchandConsumer merchandConsumer;
    private final KafkaTemplate<String,String>kafkaTemplate ;

    private final TopicReaderToBD toBD;
    @Autowired
    public MerchandController(MerchandConsumer merchandConsumer, KafkaTemplate<String, String> kafkaTemplate, TopicReaderToBD toBD) {
        this.merchandConsumer = merchandConsumer;
        this.kafkaTemplate = kafkaTemplate;
        this.toBD = toBD;
    }

    @GetMapping("/consume-csv")
    public String consumeCsvAndSendToKafka() {
        String csvFilePath = "D:\\jetbrain\\comparateurprix\\src\\main\\java\\com\\example\\comparateurprix\\Consumer\\merchand.csv"; // Provide the actual path
        String kafkaTopic = "your-kafka-topic"; // Provide the actual Kafka topic

        merchandConsumer.consumeAndSendToKafka(csvFilePath, kafkaTopic);
        return "CSV data consumed and sent to Kafka.";
    }

}
