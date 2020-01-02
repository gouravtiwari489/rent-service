package com.isobar.rentalservice.model;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "charger")
public class Charger {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer      id;
  private String       address;

  //@OneToMany(targetEntity=Type.class, mappedBy="charger", fetch= FetchType.EAGER)
  @ElementCollection
  @CollectionTable(name="types", joinColumns=@JoinColumn(name="id"))
  @Column(name="type")
  private List<String> type;
  private String       manName;

  public List<String> getType() {
    return type;
  }

  public void setType(final List<String> type) {
    this.type = type;
  }

  public Integer getId() {
    return id;
  }

  public void setId(final Integer id) {
    this.id = id;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(final String address) {
    this.address = address;
  }

  public String getManName() {
    return manName;
  }

  public void setManName(final String manName) {
    this.manName = manName;
  }
}
