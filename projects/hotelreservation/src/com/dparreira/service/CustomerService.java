package com.dparreira.service;

import com.dparreira.model.Customer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {

  private static CustomerService instance;
  private Map<String, Customer> customerMap;

  private CustomerService() {
    customerMap = new HashMap<>();
  }

  public static synchronized CustomerService getInstance() {
    if (instance == null) instance = new CustomerService();
    return instance;
  }

  public void addCustomer(String email, String firstName, String lastName) {
    customerMap.put(email, new Customer(firstName, lastName, email));
  }

  public Customer getCustomer(String customerEmail) {
    return customerMap.get(customerEmail);
  }

  public Collection<Customer> getAllCustomers() {
    return customerMap.values();
  }
}
