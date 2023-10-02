package com.retailexpert.salesanalysissystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Month;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalesAnalysisDTO {
    private String chain;
    private String category;
    private Month month;
    private BigDecimal totalRegularSales;
    private BigDecimal totalPromoSales;
    private BigDecimal promoSalesPercentage;
}
