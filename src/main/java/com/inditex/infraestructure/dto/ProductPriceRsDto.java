package com.inditex.infraestructure.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductPriceRsDto
{

    private Integer productId;
    private Integer brandId;
    private Integer priceList;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private BigDecimal price;

}