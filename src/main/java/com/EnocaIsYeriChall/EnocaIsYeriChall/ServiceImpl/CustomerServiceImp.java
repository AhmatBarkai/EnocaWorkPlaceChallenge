package com.EnocaIsYeriChall.EnocaIsYeriChall.ServiceImpl;

import com.EnocaIsYeriChall.EnocaIsYeriChall.Entity.Customer;
import com.EnocaIsYeriChall.EnocaIsYeriChall.Repository.CustomerRepository;
import com.EnocaIsYeriChall.EnocaIsYeriChall.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return  customerRepository.findById(id).get();
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(Customer customer, Long id) {
        Customer isCustomerPresent = customerRepository.findById(id).get();
        if(isCustomerPresent!=null){
            isCustomerPresent.setAge(customer.getAge());
            isCustomerPresent.setName(customer.getName());
            isCustomerPresent.setOrders(customer.getOrders());
        }

        customerRepository.save(isCustomerPresent);
    return isCustomerPresent;
    }

    @Override
    public void deletCustomer(Long id) {
        Customer isCustomerPresent =  customerRepository.findById(id).get();
        if(isCustomerPresent!=null){
            customerRepository.deleteById(id);
        }
    }

    @Override
    public List<Customer> getCustomersWithoutOrder() {
        return customerRepository.getAllCustomersWithoutOrder();
    }

    @Override
    public List<Customer> findCustomerByPartofName(String customer_name) {
        return customerRepository.findByPartOfName(customer_name);
    }


}
