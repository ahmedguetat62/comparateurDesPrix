package com.example.comparateurprix.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class MerchandConsumer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public MerchandConsumer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void consumeAndSendToKafka(String csvFilePath, String kafkaTopic) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Assuming CSV format: sku,name,url,image_url,price,currency, ...
                String[] data = line.split(",");
                String sku = data[0];
                String name = data[1];
                // etc ...

                String message = sku + "," + name;  // Adjust as needed
                kafkaTemplate.send(kafkaTopic, sku, message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
