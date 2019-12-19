package com.isobar.rentalservice.service;

import com.isobar.rentalservice.Repositories.CarRespository;
import com.isobar.rentalservice.Repositories.DriverRespository;
import com.isobar.rentalservice.model.Car;
import com.isobar.rentalservice.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {

  @Autowired
  private DriverRespository driverRespository;

  public Driver save(final Driver driver){
    return driverRespository.save(driver);
  }

  public Driver getById(Integer id){
    return driverRespository.getOne(id);
  }

  public Driver update(Driver driver, Driver driverInput){
    driver.setDriverAge(driverInput.getDriverAge());
    return driverRespository.save(driver);
  }

  public void delete(Integer id) {
    driverRespository.deleteById(id);
  }
}
