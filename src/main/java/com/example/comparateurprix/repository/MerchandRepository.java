package com.example.comparateurprix.repository;

import com.example.comparateurprix.model.Marchand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchandRepository extends JpaRepository<Marchand,String> {
    Marchand findBySku(String sku);
}
