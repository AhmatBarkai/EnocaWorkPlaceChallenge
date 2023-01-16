package com.EnocaIsYeriChall.EnocaIsYeriChall.Service;

import com.EnocaIsYeriChall.EnocaIsYeriChall.Entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    Customer getCustomerById(Long id);
    List<Customer> getAllCustomers();

    Customer updateCustomer(Customer customer,Long id);

    void deletCustomer(Long id);

    List<Customer> getCustomersWithoutOrder();
    List<Customer> findCustomerByPartofName(String customer_name);





}
