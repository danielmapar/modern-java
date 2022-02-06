package com.dparreira.service;

import com.dparreira.model.Customer;
import com.dparreira.model.IRoom;
import com.dparreira.model.Reservation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReservationService {

  private Map<String, IRoom> roomMap;
  private List<Reservation> reservations;

  private static ReservationService instance;

  private ReservationService() {
    roomMap = new HashMap<>();
    reservations = new ArrayList<>();
  }

  public static synchronized ReservationService getInstance() {
    if (instance == null) instance = new ReservationService();
    return instance;
  }

  public void addRoom(IRoom room) {
    roomMap.put(room.getRoomNumber(), room);
  }

  public IRoom getARoom(String roomId) {
    if (roomMap.containsKey(roomId)) return roomMap.get(roomId);
    return null;
  }

  public Collection<IRoom> getAllRooms() {
    return roomMap.values();
  }

  public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate)
      throws Exception {
    List<IRoom> roomsNotAvailable = (ArrayList<IRoom>) findRooms(checkInDate, checkOutDate);

    for (IRoom roomNotAvailable : roomsNotAvailable) {
      if (roomNotAvailable.equals(room)) throw new Exception("Room already reserved!");
    }

    Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
    reservations.add(reservation);
    return reservation;
  }

  public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
    List<IRoom> roomList = new ArrayList<>();
    for (Reservation reservation : reservations) {
      if (reservation.getCheckInDate().getTime() >= checkInDate.getTime() &&
          reservation.getCheckOutDate().getTime() <= checkOutDate.getTime()) {
        roomList.add(reservation.getRoom());
      }
    }
    return roomList;
  }

  public Collection<Reservation> getCustomerReservation(Customer customer) {
    // I am using a Java Streams and Lambdas, learned this on section two of the course
    return reservations.stream().filter(reservation ->
      reservation.getCustomer().equals(customer)
    ).collect(Collectors.toCollection(ArrayList::new));
  }

  public void printAllReservations() {
    System.out.print(reservations);
  }
}
