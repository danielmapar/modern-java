package com.dparreira.model;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
  private final String firstName;
  private final String lastName;
  public String email;

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

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Customer customer = (Customer) o;
    return Objects.equals(firstName, customer.firstName) &&
        Objects.equals(lastName, customer.lastName) &&
        Objects.equals(email, customer.email);
  }

  @Override public int hashCode() {
    return Objects.hash(firstName, lastName, email);
  }
}
