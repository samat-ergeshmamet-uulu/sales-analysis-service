package com.retailexpert.salesanalysissystem.service;

import com.retailexpert.salesanalysissystem.dto.DailySalesDTO;
import com.retailexpert.salesanalysissystem.dto.SalesAnalysisDTO;
import com.retailexpert.salesanalysissystem.model.FactShipment;
import com.retailexpert.salesanalysissystem.repository.FactShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class FactShipmentService {
    private final FactShipmentRepository factShipmentRepository;
    @Transactional
    public List<DailySalesDTO> getDailySales(List<String> chains, List<String> products) {
        List<FactShipment> filteredFactShipments = factShipmentRepository.findAllByCustomerChainNameInAndProductProductNameIn(chains, products);

        // Группировка по дате и признаку промо
        Map<LocalDate, Map<String, List<FactShipment>>> groupedData = filteredFactShipments.stream()
                .collect(Collectors.groupingBy(
                        FactShipment::getShipmentDate,
                        Collectors.groupingBy(
                                FactShipment::getPromoMarker,
                                Collectors.toList()
                        )
                ));
        // Проход по сгруппированным данным и вычисление анализа

        return groupedData.entrySet().stream()
                .flatMap(entry -> {
                    LocalDate date = entry.getKey();
                    Map<String, List<FactShipment>> promoData = entry.getValue();
                    BigDecimal totalRegularSales = promoData.getOrDefault("Regular", Collections.emptyList())
                            .stream()
                            .map(FactShipment::getShipmentPrice)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    BigDecimal totalPromoSales = promoData.getOrDefault("Promo", Collections.emptyList())
                            .stream()
                            .map(FactShipment::getShipmentPrice)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                    return Stream.of(new DailySalesDTO(date, totalRegularSales, totalPromoSales));
                })
                .collect(Collectors.toList());
    }

    public List<SalesAnalysisDTO> getSalesAnalysis() {
        List<FactShipment> factShipments = factShipmentRepository.findAll();

        // Группировка по сети, категории, месяцу и признаку промо
        Map<String, Map<String, Map<Month, Map<String, List<FactShipment>>>>> groupedData = factShipments.stream()
                .collect(Collectors.groupingBy(
                        factShipment -> factShipment.getCustomer().getChainName(),
                        Collectors.groupingBy(
                                factShipment -> factShipment.getProduct().getCategory(),
                                Collectors.groupingBy(
                                        factShipment -> factShipment.getShipmentDate().getMonth(),
                                        Collectors.groupingBy(
                                                FactShipment::getPromoMarker,
                                                Collectors.toList()
                                        )
                                )
                        )
                ));

        // Проход по сгруппированным данным и вычисление анализа

        return groupedData.entrySet().stream()
                .flatMap(networkEntry -> networkEntry.getValue().entrySet().stream()
                        .flatMap(categoryEntry -> categoryEntry.getValue().entrySet().stream()
                                .flatMap(monthEntry -> {
                                    String network = networkEntry.getKey();
                                    String category = categoryEntry.getKey();
                                    Month month = monthEntry.getKey();
                                    List<FactShipment> promoFactShipments = monthEntry.getValue().get("Promo");
                                    List<FactShipment> regularFactShipments = monthEntry.getValue().get("Regular");
                                    BigDecimal totalRegularSales = regularFactShipments == null ?
                                            BigDecimal.ZERO :
                                            regularFactShipments.stream()
                                                    .map(FactShipment::getShipmentPrice)
                                                    .reduce(BigDecimal.ZERO, BigDecimal::add);
                                    BigDecimal totalPromoSales = promoFactShipments == null ?
                                            BigDecimal.ZERO :
                                            promoFactShipments.stream()
                                                    .map(FactShipment::getShipmentPrice)
                                                    .reduce(BigDecimal.ZERO, BigDecimal::add);

                                    BigDecimal totalSales = totalRegularSales.add(totalPromoSales);
                                    BigDecimal promoSalesPercentage = totalSales.equals(BigDecimal.ZERO) ?
                                            BigDecimal.ZERO : totalPromoSales.divide(totalSales, 2, BigDecimal.ROUND_HALF_UP)
                                            .multiply(BigDecimal.valueOf(100));

                                    return Stream.of(new SalesAnalysisDTO(network, category, month, totalRegularSales, totalPromoSales, promoSalesPercentage));
                                })
                        )
                )
                .toList();
    }
}