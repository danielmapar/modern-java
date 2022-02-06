package com.dparreira.model;

public class Room implements IRoom {

  String roomNumber;
  Double price;
  RoomType enumeration;

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
    return "Room{" +
        "roomNumber='" + roomNumber + '\'' +
        ", price=" + price +
        ", enumeration=" + enumeration +
        '}';
  }
}
