package com.dparreira.cmd;

import com.dparreira.model.Customer;
import com.dparreira.model.IRoom;
import com.dparreira.model.Reservation;
import com.dparreira.service.CustomerService;
import com.dparreira.service.ReservationService;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class MainMenu {

  static SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);

  public static void printOptions() {
    System.out.println(
        "Welcome to the Hotel Reservation Application\n"
        + "-------------------------------\n"
        + "1. Find and reserve a room\n"
        + "2. See my reservation\n"
        + "3. Create an account\n"
        + "4. Admin\n"
        + "5. Exit\n"
        + "-------------------------------\n");
    Integer option = MainMenu.selectAnOption();
    triggerOption(option);
  }

  public static String getInput() {
    Scanner scanner = new Scanner(System.in);
    String option = scanner.nextLine();
    return option;
  }

  public static Integer selectAnOption() {
    do {
      System.out.println("Please select an option from the menu");
      Integer option = Integer.parseInt(getInput());

      if (option >= 1 && option <= 5) return option;
      else System.out.println("Invalid option, try it again!");

    } while (true);
  }

  public static Customer findACustomer() {
    do {
      System.out.println("Please enter a customer email");
      String email = getInput();
      Customer customer = CustomerService.getInstance().getCustomer(email);

      if (customer == null) {
        System.out.println("Customer email not found, try again!");
        continue;
      }
      return customer;

    } while (true);
  }

  public static void reserveARoom() {

    Date checkInDate = null;
    Date checkOutDate = null;

    Date currentDate = new Date(System.currentTimeMillis());
    Date nextYearDate = currentDate;

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(nextYearDate);
    calendar.add(Calendar.YEAR, 1);
    nextYearDate = calendar.getTime();

    do {
      try {
        System.out.println("Please enter a Check In date (mm/dd/yyyy)");
        checkInDate = formatter.parse(getInput());

        if (checkInDate.before(currentDate) || checkInDate.after(nextYearDate)) throw new Exception();
      } catch (Exception e) {
        System.out.println("Invalid Check In date, try again!");
        continue;
      }
      break;
    } while(true);

    do {
      try {
        System.out.println("Please enter a Check Out date (mm/dd/yyyy)");
        checkOutDate = formatter.parse(getInput());

        if (checkOutDate.before(currentDate) || checkOutDate.before(checkInDate) ||
            !checkOutDate.after(checkInDate) || checkOutDate.after(nextYearDate))
          throw new Exception();
      } catch (Exception e) {
        System.out.println("Invalid Check Out date, try again!");
        continue;
      }
      break;
    } while(true);

    Collection<IRoom> availableRooms = ReservationService.getInstance().findRooms(checkInDate, checkOutDate);

    if (availableRooms.size() > 0) {
      for (IRoom room : availableRooms) {
        System.out.println(room);
      }
    } else {
      System.out.println("No available rooms for the provided dates. Try again!");
      reserveARoom();
      return;
    }

    do {
      String wantsToReserveARoom;
      try {
        System.out.println("Would you like to book a room? y/n");
        wantsToReserveARoom = getInput();
        if (wantsToReserveARoom.toLowerCase().equals("y")) break;
        else if (wantsToReserveARoom.toLowerCase().equals("n")) return;
        else throw new Exception();
      } catch (Exception e) {
        System.out.println("Please enter Y (Yes) or N (No)!");
      }
    } while(true);

    do {
      String hasAccount;
      try {
        System.out.println("Do you have an account with us? y/n");
        hasAccount = getInput();
        if (hasAccount.toLowerCase().equals("y")) break;
        else if (hasAccount.toLowerCase().equals("n")) createCustomerAccount();
        else throw new Exception();
      } catch (Exception e) {
        System.out.println("Please enter Y (Yes) or N (No)!");
      }
    } while(true);

    Customer customer = findACustomer();
    IRoom room;

    do {
      String roomNumber;
      System.out.println("What room number would you like to reserve?");
      roomNumber = getInput();
      room = ReservationService.getInstance().getARoom(roomNumber);
      if (room == null) {
        System.out.println("Room not found. try again!");
        continue;
      }
      break;
    } while(true);

    try {
      Reservation reservation = ReservationService.getInstance().reserveARoom(customer, room, checkInDate, checkOutDate);
      System.out.println(reservation);
    } catch (Exception e) {
      System.out.println("Room not available at this date, try again!");
      reserveARoom();
    }
  }

  public static void findCustomerReservations() {
    Customer customer = findACustomer();

    Collection<Reservation> reservationList = ReservationService.getInstance().getCustomerReservation(customer);

    for (Reservation reservation : reservationList) {
      System.out.println(reservation);
      System.out.println("-----------");
    }
  }

  public static void createCustomerAccount() {
    do {
      System.out.println("Please enter your email (format: name@domain.com)");
      String email = getInput();
      if (email.length() == 0) {
        System.out.println("Invalid email, try again!");
        continue;
      }
      System.out.println("Please enter your first name");
      String firstName = getInput();
      if (firstName.length() == 0) {
        System.out.println("Invalid first name, try again!");
        continue;
      }
      System.out.println("Please enter your last name");
      String lastName = getInput();
      if (lastName.length() == 0) {
        System.out.println("Invalid last name, try again!");
        continue;
      }

      try {
        CustomerService.getInstance().addCustomer(email, firstName, lastName);
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid email, try again!");
        continue;
      }
      break;
    } while(true);
  }

  public static void presentAdminMenu() {
    AdminMenu.printOptions();
  }

  public static void triggerOption(Integer option) {
    switch (option) {
      case 1:
        reserveARoom();
        break;
      case 2:
        findCustomerReservations();
        break;
      case 3:
        createCustomerAccount();
        break;
      case 4:
        presentAdminMenu();
        break;
      case 5:
        System.exit(0);
        break;
      default:
        System.out.println("Invalid option!");
        break;
    }
    printOptions();
  }
}
