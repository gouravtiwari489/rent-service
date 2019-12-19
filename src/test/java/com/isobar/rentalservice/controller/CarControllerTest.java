package com.isobar.rentalservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isobar.rentalservice.model.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class CarControllerTest {

  @Autowired
  private MockMvc mockMvc;

  /*@Test
  public void shouldAddCar() throws Exception {

    Car car = new Car();
    car.setModelNumber(123);
    mockMvc
        .perform(MockMvcRequestBuilders
                     .post("/car")
                     .content(asJsonString(car))
                     .contentType(MediaType.APPLICATION_JSON)
                     .accept(MediaType.APPLICATION_JSON))
        .andExpect(result -> result.getResponse().getStatus());


  }*/

  private static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}


