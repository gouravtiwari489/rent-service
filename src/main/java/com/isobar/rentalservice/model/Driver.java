package com.isobar.rentalservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "driver")
public class  Driver {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "driverName")
  private String  driverName;

  @Column(name = "driverAge")
  private String  driverAge;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "car_id", referencedColumnName = "id")
  private Car car;



  public Integer getId() {
    return id;
  }

  public void setId(final Integer id) {
    this.id = id;
  }

  public String getDriverName() {
    return driverName;
  }

  public void setDriverName(final String driverName) {
    this.driverName = driverName;
  }

  public String getDriverAge() {
    return driverAge;
  }

  public void setDriverAge(final String driverAge) {
    this.driverAge = driverAge;
  }

  public Car getCar() {
    return car;
  }

  public void setCar(final Car car) {
    this.car = car;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Driver)) {
      return false;
    }
    final Driver driver = (Driver) o;
    return id.equals(driver.id) &&
           driverName.equals(driver.driverName) &&
           driverAge.equals(driver.driverAge) &&
           car.equals(driver.car);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, driverName, driverAge, car);
  }
}