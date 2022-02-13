package com.dparreira.model;

public class Room implements IRoom {

  String roomNumber;
  Double price;
  RoomType enumeration;

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
}
