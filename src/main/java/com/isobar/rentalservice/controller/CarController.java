package com.isobar.rentalservice.controller;

import com.isobar.rentalservice.model.Car;
import com.isobar.rentalservice.service.CarService;
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
@RequestMapping("/car")
public class CarController {

  @Autowired
  private CarService carService;

  @PostMapping
  public ResponseEntity<Car> addCar(@Valid @RequestBody Car car){
    ResponseEntity responseEntity;
    if(Objects.nonNull(car)){
      Car carResponse = carService.save(car);
      if(Objects.nonNull(carResponse)){
        responseEntity = new ResponseEntity<>(carService.save(car), HttpStatus.CREATED);
      }else{
        responseEntity = new ResponseEntity<>("Error saving car details ", HttpStatus.INTERNAL_SERVER_ERROR);
      }

    } else{
      responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return responseEntity;

  }

  @GetMapping("/{id}")
  public ResponseEntity<Car> fetchCar(@PathVariable Integer id){
    ResponseEntity responseEntity;
    if(Objects.nonNull(id)){
      Car carResponse = carService.getById(id);
      if(Objects.nonNull(carResponse)){
        responseEntity = new ResponseEntity<>(carResponse, HttpStatus.OK);
      }else{
        responseEntity = new ResponseEntity<>("Error fetching car details ", HttpStatus.INTERNAL_SERVER_ERROR);
      }

    }else{
      responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return responseEntity;
  }

  @PutMapping("/id")
  public ResponseEntity<?> updateCar(@PathVariable Integer id, @Valid @RequestBody Car car){
    ResponseEntity responseEntity;
    if(Objects.nonNull(car)){
      Car carResponse = carService.getById(car.getId());
      if(Objects.nonNull(carResponse)){
        responseEntity = new ResponseEntity<>(carService.update(carResponse, car), HttpStatus.OK);
      }else{
        responseEntity = new ResponseEntity<>("Error updating car details ", HttpStatus.INTERNAL_SERVER_ERROR);
      }

    } else{
      responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return responseEntity;

  }

  @DeleteMapping("/id")
  public void delete(@PathVariable Integer id){
    ResponseEntity responseEntity;
    if(Objects.nonNull(id)){

      if(Objects.nonNull(carService.getById(id))){
        carService.delete(id);
        responseEntity = new ResponseEntity<>("car by id:"+id +"successfully deleted ", HttpStatus.OK);
      }else{
        responseEntity = new ResponseEntity<>("car by id:"+id +" not found ", HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }else{
      responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

}
