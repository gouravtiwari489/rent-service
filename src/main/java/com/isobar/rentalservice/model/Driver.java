package com.isobar.rentalservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Driver {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "driverName")
  private String  driverName;

  @Column(name = "driverAge")
  private String  driverAge;

  /*@OneToMany(targetEntity=TimeSlot.class, mappedBy="driver", fetch= FetchType.EAGER)
  private List<TimeSlot> timeSlots;*/

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "car_id", referencedColumnName = "id")
  private Car car;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "contact_number", referencedColumnName = "contactNumber")
  private Booking booking;

  public Booking getBooking() {
    return booking;
  }

  public void setBooking(final Booking booking) {
    this.booking = booking;
  }

  /*public List<TimeSlot> getTimeSlots() {
    return timeSlots;
  }

  public void setTimeSlots(final List<TimeSlot> timeSlots) {
    this.timeSlots = timeSlots;
  }*/

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
