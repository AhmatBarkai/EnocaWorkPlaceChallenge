package com.EnocaIsYeriChall.EnocaIsYeriChall.Controller;

import com.EnocaIsYeriChall.EnocaIsYeriChall.Entity.Customer;
import com.EnocaIsYeriChall.EnocaIsYeriChall.Repository.CustomerRepository;
import com.EnocaIsYeriChall.EnocaIsYeriChall.Response.Response;
import com.EnocaIsYeriChall.EnocaIsYeriChall.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;


    @PostMapping("/saveCustomer")
    public ResponseEntity<Response> saveCustomer(@RequestBody Customer customer){
        customerService.saveCustomer(customer);
        String newCustomerName = customer.getName();
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMsg(newCustomerName+" is saved successfully");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    //and I have a question why not two types if I want to reply when I did not get a customer like a message???
    @GetMapping("/getOneCustomer/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id){
        Customer customer = customerService.getCustomerById(id);
        Response response = new Response();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customer);
    }

    @GetMapping("/getAllCustomers")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity<Response> updateCustomer(@RequestBody Customer customer,@PathVariable Long id){

        Response response = new Response();

        try{
            customerService.updateCustomer(customer,id);
            String updatedCustomerName = customer.getName();
            response.setStatusCode("200");
            response.setStatusMsg(updatedCustomerName+ " " + " is updated successfully ");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);

        }catch (Exception e){
            response.setStatusCode("400");
            response.setStatusMsg("Sorry !!! No such a customer to update");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);
        }


        }

        @DeleteMapping("/deleteCustomer/{id}")
        public ResponseEntity<Response> deleteCustomer(@PathVariable Long id){
            Response response = new Response();
            try {
                customerService.deletCustomer(id);
                response.setStatusCode("200");
                response.setStatusMsg("Customer deleted successfully.");
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(response);

            }catch (Exception e){
                response.setStatusCode("400");
                response.setStatusMsg("Customer not found ");
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(response);
            }
        }

        @GetMapping("/getCustomerWithoutOrder")
        public List<Customer> customersWithoutOrder(){
            return customerService.getCustomersWithoutOrder();
        }

        @GetMapping("/getCustomerByPartOfName")
        public List<Customer> customerByPartOfName(@RequestBody Customer customer){
            if(customer!=null && customer.getName()!=null){
                return customerService.findCustomerByPartofName(customer.getName());
            }
            else {
                return null;
            }
        }


}
