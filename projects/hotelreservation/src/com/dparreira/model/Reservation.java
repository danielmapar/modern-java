package com.dparreira.model;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

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

  private final IRoom room;
  private final Date checkInDate;
  private final Date checkOutDate;

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

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Reservation that = (Reservation) o;
    return Objects.equals(customer, that.customer) &&
        Objects.equals(room, that.room) &&
        Objects.equals(checkInDate, that.checkInDate) &&
        Objects.equals(checkOutDate, that.checkOutDate);
  }

  @Override public int hashCode() {
    return Objects.hash(customer, room, checkInDate, checkOutDate);
  }
}
