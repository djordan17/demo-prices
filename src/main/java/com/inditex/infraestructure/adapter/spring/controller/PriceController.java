package com.inditex.infraestructure.adapter.spring.controller;


import com.inditex.application.exception.NotFoundException;
import com.inditex.application.service.PriceServiceUseCase;
import com.inditex.infraestructure.dto.ProductPriceRsDto;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
public class PriceController {

  private final PriceServiceUseCase priceServiceUseCase;
  private final ModelMapper modelMapper;

  @Autowired
  public PriceController(PriceServiceUseCase priceServiceUseCase,
      @Qualifier("productPriceDtoMapper") ModelMapper modelMapper) {
    this.priceServiceUseCase = priceServiceUseCase;
    this.modelMapper = modelMapper;
  }

  @GetMapping("/brands/{brandId}/products/{productId}/prices")
  @ResponseBody
  public ProductPriceRsDto getProductByIdAndDateTime(@PathVariable Integer brandId,
      @PathVariable Integer productId,
      @RequestParam(name = "date_time", required = true)
      @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
          LocalDateTime dateTime)
      throws Exception {
    try {
      log.info("Date time parse to LocalDateTime: {}", dateTime);
      return modelMapper.map(priceServiceUseCase
              .findPriceOfProduct(brandId, productId, dateTime),
          ProductPriceRsDto.class
      );
    } catch (NotFoundException exception) {
      //TODO: Change to @ControllerAdvice or other mechanism
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Price not Found", exception);
    }

  }

}
