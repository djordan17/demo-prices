package com.inditex.application.config;

import com.inditex.domain.Price;
import com.inditex.infraestructure.dto.ProductPriceRsDto;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ModelMapperConfig
{

    @Bean( name = "defaultModelMapper")
    public ModelMapper defaultModelMapper() {
        return new ModelMapper();
    }

    @Bean( name = "productPriceDtoMapper")
    public ModelMapper productPriceDtoMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        toProductPriceRsDto(modelMapper);
        modelMapper.validate();
        return modelMapper;
    }

    private void toProductPriceRsDto(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Price.class, ProductPriceRsDto.class)
                   .addMapping(Price::getProductId, ProductPriceRsDto::setProductId)
                   .addMapping(Price::getBrandId, ProductPriceRsDto::setBrandId)
                   .addMapping(Price::getPriceList, ProductPriceRsDto::setPriceList)
                   .addMapping(Price::getStartDate, ProductPriceRsDto::setStartDateTime)
                   .addMapping(Price::getEndDate, ProductPriceRsDto::setEndDateTime)
                   .addMapping(Price::getPrice, ProductPriceRsDto::setPrice);
    }

}

