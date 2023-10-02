package com.retailexpert.salesanalysissystem.controller;

import com.retailexpert.salesanalysissystem.dto.PriceDTO;
import com.retailexpert.salesanalysissystem.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/finance")
@RequiredArgsConstructor
public class FinanceController {
    private final PriceService priceService;

    @GetMapping("/prices")
    public List<PriceDTO> getAllPrices() {
        return priceService.getAllPrices();
    }

    @GetMapping("/prices/{id}")
    public ResponseEntity<PriceDTO> getPriceById(@PathVariable Long id) {
        PriceDTO price = priceService.getPriceById(id);
        return ResponseEntity.ok(price);
    }

    @PostMapping("/prices")
    public ResponseEntity<PriceDTO> createPrice(@RequestBody PriceDTO priceDTO) {
        PriceDTO createdPrice = priceService.createPrice(priceDTO);
        return ResponseEntity.ok(createdPrice);
    }

    @PutMapping("/prices/{id}")
    public ResponseEntity<PriceDTO> updatePrice(@PathVariable Long id, @RequestBody PriceDTO updatedPriceDTO) {
        PriceDTO updatedPrice = priceService.updatePrice(id, updatedPriceDTO);
        return ResponseEntity.ok(updatedPrice);
    }

    @DeleteMapping("/prices/{id}")
    public ResponseEntity<?> deletePrice(@PathVariable Long id) {
        priceService.deletePrice(id);
        return ResponseEntity.ok().build();
    }
}
