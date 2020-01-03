package com.isobar.rentalservice.controller;

import com.isobar.rentalservice.Repositories.BookingRepository;
import com.isobar.rentalservice.Repositories.CarRespository;
import com.isobar.rentalservice.Repositories.DriverRespository;
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
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/booking")
public class BookingController {

  @Autowired
  private DriverService driverService;
  @Autowired
  private CarService carService;
  @Autowired
  private BookingRepository bookingRepository;
  @Autowired
  private CarRespository carRespository;
  @Autowired
  private DriverRespository driverRespository;

  @PostMapping
  public ResponseEntity<?> bookCar(@Valid @RequestBody Booking booking) {
    log.info("booking request {}", booking);
    try {
      List<Driver>  drivers =driverService.findAll();
      List<Driver> availableDrivers =  findAvailableDrivers(drivers);

      if(availableDrivers.size()>0){
        assignCarToDriver(availableDrivers, booking);

      }/*else{
        //find available drivers from confirmed slots
      }*/

      return new ResponseEntity<>(bookingRepository.findById(booking.getContactNumber()), HttpStatus.OK);
    } catch (Exception e) {
      log.error("{}", e.getMessage());
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @GetMapping()
  public ResponseEntity<?> fetchActiveBookings(){
    List<Booking> bookings = bookingRepository.findAll();
    List<Booking> activeBookings = filterActiveBookings(bookings);

    return new ResponseEntity<>(activeBookings, HttpStatus.OK);
  }

  private List<Booking> filterActiveBookings(final List<Booking> bookings) {

    return bookings.stream().filter(booking -> {
      if(booking.getDriver().getStatus()!= "AVAILABLE" && booking.getDriver().getStatus()!= null){
        return true;
      }else{
        return false;
      }
    }).collect(Collectors.toList());
  }

  private void assignCarToDriver(final List<Driver> availableDrivers, Booking booking) throws Exception {

    List<Booking> bookings = bookingRepository.findAll();

    for(Booking booking1: bookings){
      if(Objects.nonNull(booking1.getContactNumber()) && booking1.getContactNumber().equals(booking.getContactNumber())){

        throw new Exception("Don't over book MR x");
      }
    }

      availableDrivers.get(0).setBooking(booking);
      booking.setDriver(availableDrivers.get(0));
      availableDrivers.get(0).setStatus("Confirmed");
      driverService.save(availableDrivers.get(0));

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
      if(null != driver.getCar() && driver.getStatus().equals("AVAILABLE")){
        availableDrivers.add(driver);
      }
    }
    return availableDrivers;
  }
}
