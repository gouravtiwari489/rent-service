package com.isobar.rentalservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "timeslot")
public class TimeSlot {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;
  private String startTime;
  private String endTime;

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

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TimeSlot)) {
      return false;
    }
    final TimeSlot timeSlot = (TimeSlot) o;
    return startTime.equals(timeSlot.startTime) &&
           endTime.equals(timeSlot.endTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(startTime, endTime);
  }
}
