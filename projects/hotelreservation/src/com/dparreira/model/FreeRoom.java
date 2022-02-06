package com.dparreira.model;

public class FreeRoom extends Room {
  FreeRoom() {
    this.price = 0.0;
  }

  @Override public String toString() {
    return "<Free room> " + super.toString();
  }
}
