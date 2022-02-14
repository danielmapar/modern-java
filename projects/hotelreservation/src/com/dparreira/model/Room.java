package com.dparreira.model;

import java.util.Objects;

public class Room implements IRoom {

  private final String roomNumber;
  private final Double price;
  private final RoomType enumeration;

  public Room(String roomNumber, Double price, RoomType type) {
    this.roomNumber = roomNumber;
    this.price = price;
    this.enumeration = type;
  }

  @Override public String getRoomNumber() {
    return roomNumber;
  }

  @Override public Double getRoomPrice() {
    return price;
  }

  @Override public RoomType getRoomType() {
    return enumeration;
  }

  @Override public boolean isFree() {
    return price == 0;
  }

  @Override public String toString() {
    return "Room Number: " + roomNumber + " " +
        (enumeration.equals(RoomType.SINGLE) ? "Single bed" : "Double bed") +
        " Room Price: " + price;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Room room = (Room) o;
    return Objects.equals(roomNumber, room.roomNumber) &&
        Objects.equals(price, room.price) &&
        enumeration == room.enumeration;
  }

  @Override public int hashCode() {
    return Objects.hash(roomNumber, price, enumeration);
  }
}
