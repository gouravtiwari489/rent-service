package com.isobar.rentalservice.controller;

import com.isobar.rentalservice.Repositories.CarRespository;
import com.isobar.rentalservice.Repositories.DriverRespository;
import com.isobar.rentalservice.model.Car;
import com.isobar.rentalservice.model.Driver;
import com.isobar.rentalservice.service.CarService;
import com.isobar.rentalservice.service.DriverService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/driver")
public class DriverController {

  @Autowired
  private DriverService driverService;

  @Autowired
  private CarService carService;
  @Autowired
  private CarRespository carRespository;

  @Autowired
  private DriverRespository driverRespository;

  @PostMapping
  public ResponseEntity<Driver> addDriver(@Valid @RequestBody Driver driver){
    ResponseEntity responseEntity;
    if(Objects.nonNull(driver)){
      Driver driverResponse = driverService.save(driver);
      if(Objects.nonNull(driverResponse)){
        responseEntity = new ResponseEntity<>(driverService.save(driver), HttpStatus.CREATED);
      }else{
        responseEntity = new ResponseEntity<>("Error saving driver details ", HttpStatus.INTERNAL_SERVER_ERROR);
      }

    } else{
      responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return responseEntity;

  }

  @GetMapping("/{id}")
  public ResponseEntity<Driver> fetchDriver(@PathVariable Integer id){
    ResponseEntity responseEntity;
    if(Objects.nonNull(id)){
      Driver driverResponse = driverService.getById(id);

      if(Objects.nonNull(driverResponse)){
        responseEntity = new ResponseEntity<>(driverResponse, HttpStatus.OK);
      }else{
        responseEntity = new ResponseEntity<>("Error fetching driver details ", HttpStatus.INTERNAL_SERVER_ERROR);
      }

    }else{
      responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return responseEntity;
  }

  @GetMapping()
  public ResponseEntity<?> fetchAllDrivers(){
    return new ResponseEntity<>(driverService.findAll(), HttpStatus.OK);
  }

  @PutMapping("/id")
  public ResponseEntity<?> updateDriver(@PathVariable Integer id, @Valid @RequestBody Driver driver){
    ResponseEntity responseEntity;
    if(Objects.nonNull(driver)){
      Driver driverResponse = driverService.getById(driver.getId());
      if(Objects.nonNull(driverResponse)){
        responseEntity = new ResponseEntity<>(driverService.update(driverResponse, driver), HttpStatus.OK);
      }else{
        responseEntity = new ResponseEntity<>("Error updating driver details ", HttpStatus.INTERNAL_SERVER_ERROR);
      }

    } else{
      responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return responseEntity;

  }

  @GetMapping("/assignCar/{driverId}")
  public ResponseEntity<?> assignCar(@PathVariable Integer driverId, @Valid @RequestParam(name = "carId") Integer carId){

    Driver driver = driverService.getById(driverId);

    Car car = carService.getById(carId);

    //if(null == car.getDriver() && null == driver.getCar()){
      driver.setCar(car);
      car.setDriver(driver);
    //}


    carRespository.save(car);


    return null;

  }

  @DeleteMapping("/id")
  public ResponseEntity<?> delete(@PathVariable Integer id){
    ResponseEntity responseEntity;
    if(Objects.nonNull(id)){

      if(Objects.nonNull(driverService.getById(id))){
        driverService.delete(id);
        responseEntity = new ResponseEntity<>("driver by id:"+id +"successfully deleted ", HttpStatus.OK);
      }else{
        responseEntity = new ResponseEntity<>("driver by id:"+id +" not found ", HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }else{
      responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return responseEntity;
  }

}
