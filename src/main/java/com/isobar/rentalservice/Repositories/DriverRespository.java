package com.isobar.rentalservice.Repositories;

import com.isobar.rentalservice.model.Car;
import com.isobar.rentalservice.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRespository extends JpaRepository<Driver, Integer> {

}
