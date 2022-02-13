package com.dparreira.cmd;

import com.dparreira.model.Customer;
import com.dparreira.model.FreeRoom;
import com.dparreira.model.IRoom;
import com.dparreira.model.Reservation;
import com.dparreira.model.Room;
import com.dparreira.model.RoomType;
import com.dparreira.service.CustomerService;
import com.dparreira.service.ReservationService;
import java.util.Collection;
import java.util.Scanner;

public class AdminMenu implements IMenu {

  public static void printOptions() {
    System.out.println(
        "-------------------------------\n"
            + "1. See all Customers\n"
            + "2. See all Rooms\n"
            + "3. See all Reservations\n"
            + "4. Add a Room\n"
            + "5. Back to Main Menu\n"
            + "-------------------------------\n");
    Integer option = AdminMenu.selectAnOption();
    triggerOption(option);
  }

  public static String getInput() {
    Scanner scanner = new Scanner(System.in);
    String option = scanner.nextLine();
    return option;
  }

  public static Integer selectAnOption() {
    do {
      System.out.println("Please select an option from the admin menu");
      Integer option = Integer.parseInt(getInput());

      if (option >= 1 && option <= 5) return option;
      else System.out.println("Invalid option, try it again!");

    } while (true);
  }

  public static void printAllCustomers() {
    Collection<Customer> customers = CustomerService.getInstance().getAllCustomers();

    for (Customer customer : customers) {
      System.out.println(customer);
    }
  }

  public static void printAllRooms() {
    Collection<IRoom> rooms = ReservationService.getInstance().getAllRooms();

    for (IRoom room : rooms) {
      System.out.println(room);
    }
  }

  public static void printAllReservations() {
    Collection<Reservation> reservations = ReservationService.getInstance().getAllReservations();

    for (Reservation reservation : reservations) {
      System.out.println(reservation);
    }
  }

  public static void addARoom() {

    System.out.println("Please enter a room number");
    String roomNumber = getInput();
    RoomType roomType;
    Double price;

    do {
      try {
        System.out.println("Please enter price per night");
        price = Double.parseDouble(getInput());
      } catch (Exception e) {
        System.out.println("Incorrect format for price, try again!");
        continue;
      }
      break;
    } while(true);

    do {
      Integer roomTypeNum;
      try {
        System.out.println("Please enter 1 for a single room, and 2 for double");
        roomTypeNum = Integer.parseInt(getInput());
      } catch (Exception e) {
        System.out.println("Incorrect format for room type, try again!");
        continue;
      }
      if (roomTypeNum == 1) {
        roomType = RoomType.SINGLE;
      } else if (roomTypeNum == 2) {
        roomType = RoomType.DOUBLE;
      } else {
        System.out.println("Invalid option for room type, try again!");
        continue;
      }
      break;
    } while(true);

    ReservationService.getInstance().addRoom(new Room(roomNumber, price, roomType));

    do {
      String addAnotherRoom;
      try {
        System.out.println("Would you like to add another room? y/n");
        addAnotherRoom = getInput();

        if (addAnotherRoom.toLowerCase().equals("y")) addARoom();
        else if (addAnotherRoom.toLowerCase().equals("n")) return;
        else throw new Exception();

      } catch (Exception e) {
        System.out.println("Please enter Y (Yes) or N (No)!");
        continue;
      }
      break;
    } while(true);

  }

  public static void triggerOption(Integer option) {
    switch (option) {
      case 1:
        printAllCustomers();
        break;
      case 2:
        printAllRooms();
        break;
      case 3:
        printAllReservations();
        break;
      case 4:
        addARoom();
        break;
      case 5:
        return;
      default:
        break;
    }
    printOptions();
  }

}
