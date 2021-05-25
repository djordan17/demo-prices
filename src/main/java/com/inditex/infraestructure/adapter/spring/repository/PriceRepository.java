package com.inditex.infraestructure.adapter.spring.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.inditex.infraestructure.adapter.spring.entity.PriceEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, UUID>
{

    List<PriceEntity> findByBrandIdAndProductIdAndStartDateGreaterThanEqual(
            Integer brandId,
            Integer productId,
            LocalDateTime startDate);

}
