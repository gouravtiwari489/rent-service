package com.isobar.rentalservice.Repositories;

import com.isobar.rentalservice.model.Charger;
import com.isobar.rentalservice.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargerRepository extends JpaRepository<Charger, Integer> {

}
