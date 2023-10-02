package com.retailexpert.salesanalysissystem.dto;

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
public class DailySalesDTO {
    private LocalDate date;
    private BigDecimal regularSales;
    private BigDecimal promoSales;

}
