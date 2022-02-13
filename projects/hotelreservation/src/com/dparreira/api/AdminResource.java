package com.dparreira.api;

import com.dparreira.model.Customer;
import com.dparreira.model.IRoom;
import com.dparreira.service.CustomerService;
import com.dparreira.service.ReservationService;
import java.util.Collection;
import java.util.List;

public class AdminResource {

  private static AdminResource instance;

  private AdminResource() { }

  public synchronized static AdminResource getInstance() {
    if (instance == null) instance = new AdminResource();
    return instance;
  }

  public Customer getCustomer(String email) {
    return CustomerService.getInstance().getCustomer(email);
  }

  public void addRoom(List<IRoom> rooms) {
    ReservationService reservationService = ReservationService.getInstance();
    for (IRoom room : rooms) {
      reservationService.addRoom(room);
    }
  }

  public Collection<IRoom> getAllRooms() {
    return ReservationService.getInstance().getAllRooms();
  }

  public Collection<Customer> getAllCustomers() {
    return CustomerService.getInstance().getAllCustomers();
  }

}
