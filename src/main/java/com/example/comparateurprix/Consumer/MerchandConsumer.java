package com.example.comparateurprix.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

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
                String[] data = line.split(",");
                String sku = data[0];
                String name = data[1];
                String url = data[2];
                String image_url = data[3];
                String price = data[4];
                String currency = data[5];
                String brand = data[6];
                String category_id = data[7];
                String variant_group_id = data[8];
                String size = data[9];
                String color = data[10];
                String material = data[11];
                String model = data[12];
                String age_group = data[13];
                String gender = data[14];
                String operation = data[15];
                String message = sku + "," + name+ "," + url+ "," + image_url+ "," + price+ "," + currency+ "," + brand
                        + "," + category_id+ "," + variant_group_id+ "," + size+ "," +
                        color+ "," + material+ "," + model+ "," + age_group+ "," + gender+ "," + operation;  // Adjust as needed*
                kafkaTemplate.send(kafkaTopic, sku, message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
