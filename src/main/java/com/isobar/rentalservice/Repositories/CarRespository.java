package com.isobar.rentalservice.Repositories;

import com.isobar.rentalservice.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRespository extends JpaRepository<Car, Integer> {


}
