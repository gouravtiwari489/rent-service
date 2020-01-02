package com.isobar.rentalservice.controller;

import com.isobar.rentalservice.Repositories.ChargerRepository;
import com.isobar.rentalservice.model.Car;
import com.isobar.rentalservice.model.Charger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/charger")
public class ChargingController {

  @Autowired
  private ChargerRepository chargerRepository;

  @PostMapping
  public ResponseEntity<?> addCharger(@Valid @RequestBody Charger charger) {
    log.info("charger request {}", charger);
    try {
      Charger charger1 = chargerRepository.save(charger);
      log.info("car response {}", charger1);
      return new ResponseEntity<>(charger1, HttpStatus.CREATED);
    } catch (Exception e) {
      log.error("Failed to save car details {}", e.getMessage());
      return new ResponseEntity<>("Error saving charger details ", HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @GetMapping()
  public ResponseEntity<?> fetchCharger(){
    //log.info("Id {}", id);
    try{
      List<Charger> chargerList = chargerRepository.findAll();
      log.info("charger response for requested id {}", chargerList);
      return new ResponseEntity<>(chargerList, HttpStatus.OK);
    }catch (Exception e){
      log.error("Error while fetching charger details {}", e.getMessage());
      return new ResponseEntity<>("Error fetching car details ", HttpStatus.NOT_FOUND);
    }
  }

}
