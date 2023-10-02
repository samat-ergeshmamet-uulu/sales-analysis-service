package com.retailexpert.salesanalysissystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "fact_shipment")
@NoArgsConstructor
@AllArgsConstructor
public class FactShipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Product product;
    private LocalDate shipmentDate;
    private BigDecimal shipmentPrice;
    private String promoMarker; // "Regular" или "Promo"
}
