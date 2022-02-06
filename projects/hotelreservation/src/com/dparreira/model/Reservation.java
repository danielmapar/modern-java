package com.dparreira.model;

import java.util.Date;

public class Reservation {
  Customer customer;

  public Customer getCustomer() {
    return customer;
  }

  public IRoom getRoom() {
    return room;
  }

  public Date getCheckInDate() {
    return checkInDate;
  }

  public Date getCheckOutDate() {
    return checkOutDate;
  }

  public void setCheckOutDate(Date checkOutDate) {
    this.checkOutDate = checkOutDate;
  }

  IRoom room;
  Date checkInDate;
  Date checkOutDate;

  public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
    this.customer = customer;
    this.room = room;
    this.checkInDate = checkInDate;
    this.checkOutDate = checkOutDate;
  }

  @Override public String toString() {
    return "Reservation{" +
        "customer=" + customer +
        ", room=" + room +
        ", checkInDate=" + checkInDate +
        ", checkOutDate=" + checkOutDate +
        '}';
  }
}
