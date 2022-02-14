package com.dparreira.service;

import com.dparreira.model.Customer;
import com.dparreira.model.IRoom;
import com.dparreira.model.Reservation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationService {

  private HashMap<String, IRoom> roomMap;
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

  boolean hasRoom(String roomId) {
    return roomMap.containsKey(roomId);
  }

  public void addRoom(IRoom room) {
    roomMap.put(room.getRoomNumber(), room);
  }

  public IRoom getARoom(String roomId) {
    if (hasRoom(roomId)) return roomMap.get(roomId);
    return null;
  }

  public Collection<IRoom> getAllRooms() {
    return roomMap.values();
  }

  public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate)
      throws Exception {
    Collection<IRoom> availableRooms = findRooms(checkInDate, checkOutDate);
    if (!availableRooms.contains(room)) throw new Exception("Room not available!");

    Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
    reservations.add(reservation);
    return reservation;
  }

  public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
    Collection<IRoom> reservedRoomList = new ArrayList<>();
    for (Reservation reservation : reservations) {
      if ((checkInDate.getTime() >= reservation.getCheckInDate().getTime() && checkInDate.getTime() <= reservation.getCheckOutDate().getTime()) ||
          (checkOutDate.getTime() <= reservation.getCheckOutDate().getTime() && checkOutDate.getTime() >= reservation.getCheckInDate().getTime())) {
        reservedRoomList.add(reservation.getRoom());
      }
    }

    HashMap<String, IRoom> roomMapCopy = (HashMap<String, IRoom>) roomMap.clone();
    Collection<IRoom> availableRooms = roomMapCopy.values();
    availableRooms.removeAll(reservedRoomList);

    return availableRooms;
  }

  public Collection<Reservation> getCustomerReservation(Customer customer) {
    // I am using a Java Streams and Lambdas, learned this on section two of the course
    return reservations.stream().filter(reservation ->
      reservation.getCustomer().equals(customer)
    ).collect(Collectors.toCollection(ArrayList::new));
  }

  public Collection<Reservation> getAllReservations() {
    return reservations;
  }
}
