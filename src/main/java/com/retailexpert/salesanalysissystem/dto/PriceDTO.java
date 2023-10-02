package com.retailexpert.salesanalysissystem.dto;

import com.retailexpert.salesanalysissystem.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceDTO {
    private Long id;
    private String chainName;
    private Product product;
    private BigDecimal regularPrice;
}
