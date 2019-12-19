package com.isobar.rentalservice.service;

import com.isobar.rentalservice.Repositories.CarRespository;
import com.isobar.rentalservice.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

  @Autowired
  private CarRespository carRespository;

  public Car save(final Car car){
    return carRespository.save(car);
  }

  public Car getById(Integer id){
    return carRespository.getOne(id);
  }

  public Car update(Car car, Car input){
    car.setColor(input.getColor());
    return carRespository.save(car);
  }

  public void delete(Integer id) {
    carRespository.deleteById(id);
  }
}
