package com.inditex.application.service;

import com.inditex.application.exception.NotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.inditex.application.repository.PriceRepository;
import com.inditex.domain.Price;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

public class PriceServiceDefaultTest
{
    @Mock
    private PriceRepository priceRepository;
    private PodamFactory podamFactory;
    private List<Price> pricesMock;
    private Price priceMock;
    private PriceServiceUseCase priceServiceUseCase;

    @BeforeEach
    public void setUp()
    {
        initMocks(this);
        podamFactory = new PodamFactoryImpl();
        priceServiceUseCase = new PriceServiceDefault(priceRepository);

    }

    @Test
    @DisplayName("Should return the actual of the product when it exist")
    void should_return_actual_product_when_it_exist() throws Exception
    {
        priceMock = podamFactory.manufacturePojo(Price.class);
        pricesMock = Arrays.asList(priceMock);

        doReturn(pricesMock)
                .when(priceRepository)
                .findByBrandIdAndProductIdAndStartDateGreaterThanEqual(any(), any(), any());

        final Price price = priceServiceUseCase
                .findPriceOfProduct(1, priceMock.getProductId(), priceMock.getStartDate());

        assertNotNull(price);
    }

    @Test
    @DisplayName("Should return the actual of the product when exist two or more products")
    void should_return_actual_product_when_exist_two_or_more_products() throws Exception
    {
        Price price1 = new Price(
                1,
                1,
                LocalDateTime.of(2021, 06, 14, 00, 00, 00),
                LocalDateTime.of(2021, 12, 31, 23, 59, 59),
                1,
                35455,
                0,
                new BigDecimal("35.50"),
                "EUR",
                LocalDateTime.now(),
                "user01"
        );
        Price price2 = new Price(
                2,
                1,
                LocalDateTime.of(2021, 06, 14, 15, 00, 00),
                LocalDateTime.of(2021, 06, 14, 18, 30, 59),
                2,
                35455,
                1,
                new BigDecimal("25.45"),
                "EUR",
                LocalDateTime.now(),
                "user01"
        );
        pricesMock = Arrays.asList(price1, price2);

        doReturn(pricesMock)
                .when(priceRepository)
                .findByBrandIdAndProductIdAndStartDateGreaterThanEqual(any(), any(), any());

        final Price price = priceServiceUseCase
                .findPriceOfProduct(1,35455, LocalDateTime.of(2021, 06, 14, 10, 00, 00));

        assertEquals("35.50", price.getPrice().toString());
    }

    @Test
    @DisplayName("Should return exception when not found fields")
    void should_return_exception_when_bot_found_fields() throws NotFoundException
    {
        pricesMock = Collections.EMPTY_LIST;

        doReturn(pricesMock)
                .when(priceRepository)
                .findByBrandIdAndProductIdAndStartDateGreaterThanEqual(any(), any(), any());

        var exception = assertThrows(NotFoundException.class, () -> priceServiceUseCase
                .findPriceOfProduct(1 ,35455, LocalDateTime.now()));
        assertEquals("Not found", exception.getMessage());
    }

}
