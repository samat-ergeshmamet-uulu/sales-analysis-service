package com.retailexpert.salesanalysissystem.service;

import com.retailexpert.salesanalysissystem.dto.PriceDTO;
import com.retailexpert.salesanalysissystem.mapper.PriceMapper;
import com.retailexpert.salesanalysissystem.model.Price;
import com.retailexpert.salesanalysissystem.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PriceService {
    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;
    @Transactional
    public List<PriceDTO> getAllPrices() {
        List<Price> prices = priceRepository.findAll();
        return prices.stream()
                .map(priceMapper::priceToPriceDTO)
                .collect(Collectors.toList());
    }
    @Transactional
    public PriceDTO getPriceById(Long id) {
        Price price = priceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Price not found with id " + id));
        return priceMapper.priceToPriceDTO(price);
    }
    @Transactional
    public PriceDTO createPrice(PriceDTO priceDTO) {
        Price price = priceMapper.priceDTOToPrice(priceDTO);
        Price createdPrice = priceRepository.save(price);
        return priceMapper.priceToPriceDTO(createdPrice);
    }
    @Transactional
    public PriceDTO updatePrice(Long id, PriceDTO updatedPriceDTO) {
        Price updatedPrice = priceMapper.priceDTOToPrice(updatedPriceDTO);
        Price existingPrice = priceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Price not found with id " + id));

        existingPrice.setChainName(updatedPrice.getChainName());
        existingPrice.setProduct(updatedPrice.getProduct());
        existingPrice.setRegularPrice(updatedPrice.getRegularPrice());

        Price savedPrice = priceRepository.save(existingPrice);
        return priceMapper.priceToPriceDTO(savedPrice);
    }

    public void deletePrice(Long id) {
        priceRepository.deleteById(id);
    }
}
