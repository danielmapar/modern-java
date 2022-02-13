package com.dparreira.model;

import java.util.Calendar;
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
    Calendar checkInDateCalendar = Calendar.getInstance();
    checkInDateCalendar.setTime(checkInDate);

    Calendar checkOutDateCalendar = Calendar.getInstance();
    checkOutDateCalendar.setTime(checkOutDate);

    return customer.getFullName() + "\n" +
        "Room: " + room.getRoomNumber() + " - " +
        (room.getRoomType().equals(RoomType.SINGLE) ? "Single bed" : "Double bed") + "\n" +
        "Price: $" + room.getRoomPrice() + " price per night" + "\n" +
        "Checkin Date: " + (checkInDateCalendar.get(Calendar.MONTH) + 1) + "/" + checkInDateCalendar.get(Calendar.DAY_OF_MONTH) + "/" + checkInDateCalendar.get(Calendar.YEAR) + "\n" +
        "Checkout Date: " + (checkOutDateCalendar.get(Calendar.MONTH) + 1) + "/" + checkOutDateCalendar.get(Calendar.DAY_OF_MONTH) + "/" + checkOutDateCalendar.get(Calendar.YEAR);
  }
}
