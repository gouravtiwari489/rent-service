package com.isobar.rentalservice.controller;

import com.isobar.rentalservice.Repositories.BookingRepository;
import com.isobar.rentalservice.Repositories.CarRespository;
import com.isobar.rentalservice.model.Booking;
import com.isobar.rentalservice.model.Car;
import com.isobar.rentalservice.model.Driver;
import com.isobar.rentalservice.service.CarService;
import com.isobar.rentalservice.service.DriverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/book")
public class BookingController {

  @Autowired
  private DriverService driverService;
  @Autowired
  private CarService carService;
  @Autowired
  private BookingRepository bookingRepository;
  @Autowired
  private CarRespository carRespository;

  @PostMapping
  public ResponseEntity<?> addCar(@Valid @RequestBody Booking booking) {
    log.info("booking request {}", booking);
    try {

      //find list
      List<Driver>  drivers =driverService.findAll();
      List<Driver> availableDrivers =  findAvailableDrivers(drivers);

      if(availableDrivers.size()>0){
        assignCarToDriver(availableDrivers, booking);

      }else{
        //find available drivers from confirmed slots
      }

      return new ResponseEntity<>(bookingRepository.findById(booking.getContactNumber()), HttpStatus.OK);
    } catch (Exception e) {
      log.error("{}", e.getMessage());
      return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  private void assignCarToDriver(final List<Driver> availableDrivers, Booking booking) {
      availableDrivers.get(0).setBooking(booking);
      booking.setDriver(availableDrivers.get(0));
      driverService.save(availableDrivers.get(0));
      //bookingRepository.save(booking);
  }

  private List<Car> findAvailableCars(final List<Car> carList) {
    List<Car> availableCars = new ArrayList<>();
    for (Car car : carList) {
      //if(null == car.getDriver()){
        availableCars.add(car);
      //}
    }

    return availableCars;
  }

  private List<Driver> findAvailableDrivers(List<Driver> drivers) {
    List<Driver> availableDrivers = new ArrayList<>();
    for(Driver driver: drivers){
      if(null != driver.getCar()){
        availableDrivers.add(driver);
      }
    }
    return availableDrivers;
  }
}
