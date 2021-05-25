package com.inditex.application.service;

import java.time.LocalDateTime;

import com.inditex.domain.Price;

public interface PriceServiceUseCase
{

    Price findPriceOfProduct(final Integer brandId,
                             final Integer productId,
                             final LocalDateTime dateTime) throws Exception;

}
