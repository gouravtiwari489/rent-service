/*
package com.isobar.rentalservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "type")
public class Type {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  private String typename;

  @ManyToOne
  @JoinColumn(name="id")
  private Charger charger;

  public Integer getId() {
    return id;
  }

  public void setId(final Integer id) {
    this.id = id;
  }

  public Charger getCharger() {
    return charger;
  }

  public void setCharger(final Charger charger) {
    this.charger = charger;
  }

  public String getTypename() {
    return typename;
  }

  public void setTypename(final String typename) {
    this.typename = typename;
  }
}
*/
