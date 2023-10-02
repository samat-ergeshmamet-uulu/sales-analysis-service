package com.retailexpert.salesanalysissystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "price")
@NoArgsConstructor
@AllArgsConstructor
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String chainName;
    @ManyToOne
    private Product product;
    private BigDecimal regularPrice;

}
