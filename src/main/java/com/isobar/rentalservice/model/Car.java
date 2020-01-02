package com.isobar.rentalservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "car")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Car {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;
  private String  modelNumber;
  private String  brand;
  private String  color;
  private String  bookingStatus;


  /*@OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "driver_id", referencedColumnName = "id")
  *//*@OneToOne(mappedBy = "id", cascade = CascadeType.ALL)*//*
  private Driver  driver;*/

  @OneToOne(mappedBy = "car")
  private Driver driver;

  public Integer getId() {
    return id;
  }

  public void setId(final Integer id) {
    this.id = id;
  }

  /*public Driver getDriver() {
    return driver;
  }*/

  public void setDriver(final Driver driver) {
    this.driver = driver;
  }

  public String getBookingStatus() {
    return bookingStatus;
  }

  public void setBookingStatus(final String bookingStatus) {
    this.bookingStatus = bookingStatus;
  }

  public String getModelNumber() {
    return modelNumber;
  }

  public String getBrand() {
    return brand;
  }

  public String getColor() {
    return color;
  }

  public void setModelNumber(final String modelNumber) {
    this.modelNumber = modelNumber;
  }

  public void setBrand(final String brand) {
    this.brand = brand;
  }

  public void setColor(final String color) {
    this.color = color;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Car)) {
      return false;
    }
    final Car car = (Car) o;
    return id.equals(car.id) &&
           modelNumber.equals(car.modelNumber) &&
           brand.equals(car.brand) &&
           color.equals(car.color) &&
           bookingStatus.equals(car.bookingStatus) &&
           driver.equals(car.driver);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, modelNumber, brand, color, bookingStatus, driver);
  }
}
