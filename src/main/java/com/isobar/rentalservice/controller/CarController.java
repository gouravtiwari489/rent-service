package com.isobar.rentalservice.controller;

import com.isobar.rentalservice.model.Car;
import com.isobar.rentalservice.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/car")
public class CarController {

  @Autowired
  private CarService carService;

  @PostMapping
  public ResponseEntity<?> addCar(@Valid @RequestBody Car car) {
    log.info("car request {}", car);
    try {
      Car carResponse = carService.save(car);
      log.info("car response {}", carResponse);
      return new ResponseEntity<>(carResponse, HttpStatus.CREATED);
    } catch (Exception e) {
      log.error("Failed to save car details {}", e.getMessage());
      return new ResponseEntity<>("Error saving car details ", HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @GetMapping("/{id}")
  public ResponseEntity<?> fetchCar(@PathVariable Integer id){
    log.info("Id {}", id);
    try{
      Car carResponse = carService.getById(id);
      log.info("car response for requested id {}", carResponse);
      return new ResponseEntity<>(carResponse, HttpStatus.OK);
    }catch (Exception e){
      log.error("Error while fetching car details {}", e.getMessage());
      return new ResponseEntity<>("Error fetching car details ", HttpStatus.NOT_FOUND);
    }
  }


  @PutMapping("/{id}")
  public ResponseEntity<?> updateCar(@PathVariable Integer id, @Valid @RequestBody Car car){
    log.info("Id {}"+id +"&"+"Request car object {}",car);
    try{
      Car carResponse = carService.getById(car.getId());
      log.info("existing car details "+ carResponse);
      Car carUpdate = carService.update(carResponse, car);
      log.info("Updated car details {}", carUpdate);
      return new ResponseEntity<>(carUpdate, HttpStatus.OK);
    }catch(Exception e){
      log.error("Error while updating car details {}", e.getMessage());
      return new ResponseEntity<>("Error while updating car details ", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id){
    log.info("car Id to be deleted {}", id);
    try{
      carService.delete(id);
      log.info("car deleted successfully");
      return new ResponseEntity<>("car by id:"+id +"successfully deleted ", HttpStatus.OK);
    }catch(Exception e){
      log.error("Error while deleting car details {}", e.getMessage());
      return new ResponseEntity<>("Error while deleting car details ", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
