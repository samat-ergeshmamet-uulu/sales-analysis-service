package com.retailexpert.salesanalysissystem.repository;

import com.retailexpert.salesanalysissystem.model.FactShipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactShipmentRepository extends JpaRepository<FactShipment, Long> {

    List<FactShipment> findAllByCustomerChainNameInAndProductProductNameIn(List<String> chains, List<String> products);
}

