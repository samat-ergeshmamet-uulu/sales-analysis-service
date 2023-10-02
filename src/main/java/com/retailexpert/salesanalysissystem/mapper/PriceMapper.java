package com.retailexpert.salesanalysissystem.mapper;

import com.retailexpert.salesanalysissystem.dto.PriceDTO;
import com.retailexpert.salesanalysissystem.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PriceMapper {
    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "product.id", target = "productId")
    PriceDTO priceToPriceDTO(Price price);

    @Mapping(source = "customerId", target = "customer.id")
    @Mapping(source = "productId", target = "product.id")
    Price priceDTOToPrice(PriceDTO priceDTO);
}
