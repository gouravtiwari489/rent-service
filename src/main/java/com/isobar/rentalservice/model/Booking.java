package com.isobar.rentalservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "booking")
public class Booking {

  @Id
  //@GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "contactNumber")
  private Integer contactNumber;
  private String  customerName;
  private String  startTime;
  private String  endTime;

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(final String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(final String endTime) {
    this.endTime = endTime;
  }

  //private String status;

  @OneToOne(mappedBy = "booking")
  private Driver driver;

  public Driver getDriver() {
    return driver;
  }

  public void setDriver(final Driver driver) {
    this.driver = driver;
  }

  /*public String getStatus() {
    return status;
  }

  public void setStatus(final String status) {
    this.status = status;
  }*/


  public Integer getContactNumber() {
    return contactNumber;
  }

  public void setContactNumber(final Integer contactNumber) {
    this.contactNumber = contactNumber;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(final String customerName) {
    this.customerName = customerName;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Booking)) {
      return false;
    }
    final Booking booking = (Booking) o;
    return contactNumber.equals(booking.contactNumber) && customerName.equals(booking.customerName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contactNumber, customerName);
  }
}
