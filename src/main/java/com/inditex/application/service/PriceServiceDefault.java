package com.inditex.application.service;


import com.inditex.application.exception.NotFoundException;
import com.inditex.application.repository.PriceRepository;
import com.inditex.domain.Price;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PriceServiceDefault implements PriceServiceUseCase {

  private final PriceRepository priceRepository;

  @Override
  public Price findPriceOfProduct(final Integer brandId,
      final Integer productId,
      final LocalDateTime dateTime) throws Exception {
    var priceList =
        priceRepository
            .findByBrandIdAndProductIdAndStartDateGreaterThanEqual(brandId,
                productId,
                dateTime
            )
            .stream()
            .sorted(Comparator.comparing(Price::getPriority))
            //.sorted(Comparator.comparing(Price::getStartDate))
            .collect(Collectors.toList());

    return priceList.stream().findFirst().orElseThrow(() -> new NotFoundException("Not found"));
  }
}
