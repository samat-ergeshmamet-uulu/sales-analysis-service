package com.retailexpert.salesanalysissystem.dto;

import com.retailexpert.salesanalysissystem.enums.PromoStatus;
import com.retailexpert.salesanalysissystem.model.Customer;
import com.retailexpert.salesanalysissystem.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FactShipmentDTO {
    private Long id;
    private Customer customer;
    private Product product;
    private LocalDate shipmentDate;
    private BigDecimal shipmentPrice;
    private String promoMarker; // "Regular" или "Promo"
}
