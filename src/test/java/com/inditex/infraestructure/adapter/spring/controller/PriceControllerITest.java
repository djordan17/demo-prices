package com.inditex.infraestructure.adapter.spring.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerITest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
  public void test_1() throws Exception {
    mockMvc.perform(get("/brands/{brandId}/products/{productId}/prices",
        1,
        35455
    )
        .param("date_time", "2020-06-14T10:00:00")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(
            jsonPath("$.brandId")
                .value("1")
        )
        .andExpect(
            jsonPath("$.priceList")
                .value("2")
        )
        .andExpect(
            jsonPath("$.price")
                .value("25.45")
        );

  }

  @Test
  @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
  public void test_2() throws Exception {
    mockMvc.perform(get("/brands/{brandId}/products/{productId}/prices",
        1,
        35455
    )
        .param("date_time", "2020-06-14T16:00:00")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(
            jsonPath("$.brandId")
                .value("1")
        )
        .andExpect(
            jsonPath("$.priceList")
                .value("3")
        )
        .andExpect(
            jsonPath("$.price")
                .value("30.5")
        );

  }

  @Test
  @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
  public void test_3() throws Exception {
    mockMvc.perform(get("/brands/{brandId}/products/{productId}/prices",
        1,
        35455
    )
        .param("date_time", "2020-06-14T21:00:00")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(
            jsonPath("$.brandId")
                .value("1")
        )
        .andExpect(
            jsonPath("$.priceList")
                .value("3")
        )
        .andExpect(
            jsonPath("$.price")
                .value("30.5")
        );
  }

  @Test
  @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)")
  public void test_4() throws Exception {
    mockMvc.perform(get("/brands/{brandId}/products/{productId}/prices",
        1,
        35455
    )
        .param("date_time", "2020-06-14T21:00:00")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(
            jsonPath("$.brandId")
                .value("1")
        )
        .andExpect(
            jsonPath("$.priceList")
                .value("3")
        )
        .andExpect(
            jsonPath("$.price")
                .value("30.5")
        );
  }

  @Test
  @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)")
  public void test_5() throws Exception {
    mockMvc.perform(get("/brands/{brandId}/products/{productId}/prices",
        1,
        35455
    )
        .param("date_time", "2020-06-14T21:00:00")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(
            jsonPath("$.brandId")
                .value("1")
        )
        .andExpect(
            jsonPath("$.priceList")
                .value("3")
        )
        .andExpect(
            jsonPath("$.price")
                .value("30.5")
        );
  }

}
