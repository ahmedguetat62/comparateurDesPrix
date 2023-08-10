package com.example.comparateurprix.service;

import com.example.comparateurprix.model.Marchand;
import com.example.comparateurprix.repository.MerchandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service

public class MerchandService {

    private final MerchandRepository merchandRepository;
    private static final Logger LOGGER = Logger.getLogger(MerchandService.class.getName());
    @Autowired
    public MerchandService(MerchandRepository merchandRepository) {
        this.merchandRepository = merchandRepository;
    }
    private Marchand getMerchandData(String line){
        Marchand m = new Marchand() ;
        String[] data = line.split(",");
        m.setSku(data[0]);
        m.setProductName(data[1]);
        m.setProductUrl(data[2]);
        m.setImageUrl(data[3]);
        float price = 0 ;
        try{
            price = Float.parseFloat(data[4]);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        m.setPrice(price);
        m.setCurrency(data[5]);
        m.setBrand(data[6]);
        m.setCategory(data[7]);
        m.setVariantGroup(data[8]);
        m.setVariantSize(data[9]);
        m.setColor(data[10]);
        m.setMaterial(data[11]);
        m.setModel(data[12]);
        m.setAgeRange(data[13]);
        m.setGender(data[14]);
        m.setOperation(data[15]);
        return m;
    }
    public String processInsertData(String data) {
        Marchand merchand = getMerchandData(data);
        //merchand.getPrice();
        LOGGER.info("Processing line: " + merchand);
        merchandRepository.save(merchand);
        LOGGER.info("Inserted Success: " + merchand);




        return "Inserted success"+merchand.getSku();
    }

   /* public void processUpdateData(String data) {
        // Implement logic to process update data from Kafka
        // For example, parse the data, retrieve the entity to update, apply changes, and save it
        // Replace this with your actual implementation
        Marchand merchand = parseAndUpdateMerchand(data);
        merchandRepository.save(merchand);
    }

    public void processDeleteData(String data) {
        // Implement logic to process delete data from Kafka
        // For example, parse the data, retrieve the entity to delete, and delete it
        // Replace this with your actual implementation
        String sku = parseSkuFromData(data);
        merchandRepository.deleteBySku(sku);
    }*/
}
