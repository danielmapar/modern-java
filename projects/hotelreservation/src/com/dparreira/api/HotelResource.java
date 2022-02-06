package com.dparreira.api;

import com.dparreira.model.Customer;
import com.dparreira.model.IRoom;
import com.dparreira.model.Reservation;
import com.dparreira.service.CustomerService;
import com.dparreira.service.ReservationService;
import java.util.Collection;
import java.util.Date;

public class HotelResource {

  private static HotelResource instance;

  private HotelResource() { }

  public synchronized static HotelResource getInstance() {
    if (instance == null) instance = new HotelResource();
    return instance;
  }


  public Customer getCustomer(String email) {
    return CustomerService.getInstance().getCustomer(email);
  }

  public void createACustomer(String email, String firstName, String lastName) {
    CustomerService.getInstance().addCustomer(email, firstName, lastName);
  }

  public IRoom getRoom(String roomNumber) {
    return ReservationService.getInstance().getARoom(roomNumber);
  }

  public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate)
      throws Exception {
    Customer customer = CustomerService.getInstance().getCustomer(customerEmail);
    return ReservationService.getInstance().reserveARoom(customer, room, checkInDate, checkOutDate);
  }

  public Collection<Reservation> getCustomersReservations(String customerEmail) {
    Customer customer = CustomerService.getInstance().getCustomer(customerEmail);
    return ReservationService.getInstance().getCustomerReservation(customer);
  }

  public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
    return ReservationService.getInstance().findRooms(checkIn, checkOut);
  }
}
