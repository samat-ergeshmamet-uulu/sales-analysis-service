package com.retailexpert.salesanalysissystem.controller;

import com.retailexpert.salesanalysissystem.dto.DailySalesDTO;
import com.retailexpert.salesanalysissystem.dto.SalesAnalysisDTO;
import com.retailexpert.salesanalysissystem.service.FactShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/analysis")
@RequiredArgsConstructor
public class AnalysisController {
    private final FactShipmentService factShipmentService;

    @GetMapping("/sales")
    public List<SalesAnalysisDTO> getSalesAnalysis() {
        return factShipmentService.getSalesAnalysis();
    }

    @GetMapping("/sales-by-day")
    public List<DailySalesDTO> getDailySales(@RequestParam(name = "chains") List<String> chains,
                                             @RequestParam(name = "products") List<String> products) {
        return factShipmentService.getDailySales(chains, products);
    }
}

