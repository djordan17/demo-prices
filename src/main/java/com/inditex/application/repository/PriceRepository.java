package com.inditex.application.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.inditex.domain.Price;

public interface PriceRepository
{

    List<Price> findByBrandIdAndProductIdAndStartDateGreaterThanEqual(
            Integer brandId, Integer productId, LocalDateTime startDate);
}
