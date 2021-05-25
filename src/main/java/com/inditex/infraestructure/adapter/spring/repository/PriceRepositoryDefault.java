package com.inditex.infraestructure.adapter.spring.repository;

import com.inditex.domain.Price;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PriceRepositoryDefault implements com.inditex.application.repository.PriceRepository {

  private final ModelMapper modelMapper;
  private final PriceRepository priceRepository;

  @Autowired
  public PriceRepositoryDefault(@Qualifier("defaultModelMapper") ModelMapper modelMapper,
      PriceRepository priceRepository) {
    this.modelMapper = modelMapper;
    this.priceRepository = priceRepository;
  }

  @Override
  public List<Price> findByBrandIdAndProductIdAndStartDateGreaterThanEqual(
      Integer brandId,
      Integer productId,
      LocalDateTime startDate) {
    return priceRepository
        .findByBrandIdAndProductIdAndStartDateGreaterThanEqual(brandId,
            productId,
            startDate
        )
        .stream()
        .map(price -> modelMapper.map(price, Price.class))
        .collect(Collectors.toUnmodifiableList());
  }
}
