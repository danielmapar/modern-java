package com.dparreira.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
  String firstName;
  String lastName;
  String email;

  public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
      Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

  public static boolean isEmailValid(String emailStr) {
    Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
    return matcher.find();
  }

  public Customer(String firstName, String lastName, String email) {
    if (!isEmailValid(email)) throw new IllegalArgumentException();
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public String getEmail() {
    return this.email;
  }

  public String getFullName() {
    return firstName + " " + lastName;
  }

  @Override public String toString() {
    return "First Name: " + firstName + " Last Name: " + lastName + " Email: " + email;
  }
}
