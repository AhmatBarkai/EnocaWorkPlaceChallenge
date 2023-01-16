package com.EnocaIsYeriChall.EnocaIsYeriChall.Controller;

import com.EnocaIsYeriChall.EnocaIsYeriChall.Entity.Customer;
import com.EnocaIsYeriChall.EnocaIsYeriChall.Entity.Order;
import com.EnocaIsYeriChall.EnocaIsYeriChall.Repository.OrderRepository;
import com.EnocaIsYeriChall.EnocaIsYeriChall.Response.Response;
import com.EnocaIsYeriChall.EnocaIsYeriChall.Service.CustomerService;
import com.EnocaIsYeriChall.EnocaIsYeriChall.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerService customerService;

    @PostMapping("/saveOrder/{customer_id}")
    public ResponseEntity<Response> saveOrderToCustomer(@RequestBody Order order, @PathVariable long customer_id){

        Customer customer = customerService.getCustomerById(customer_id);
        String customerName = customer.getName();
        customer.getOrders().add(order);
        orderService.saveOrder(order);
        customerService.saveCustomer(customer);
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMsg(" Order Successfully added to " + customerName+" customer");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    @GetMapping("/getAllOrders")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }
    @GetMapping("/getOrderById/{id}")
   public ResponseEntity<Order> getOrderById(@PathVariable Long id){
        Response response = new Response();
        Order order = orderService.getOrderById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(order);
    }

    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<Response> deleteOrder(@PathVariable Long id){
        Response response = new Response();
        try {
            orderService.deleteOrder(id);
            response.setStatusCode("200");
            response.setStatusMsg("Order is deleted successfully from the database .");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        }catch(Exception e){
            response.setStatusCode("400");
            response.setStatusMsg("Sorry ! No such an order in the database .");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);
        }

    }
    @PutMapping("/updateOrder/{id}")
    public ResponseEntity<Response> updateOrder(@RequestBody Order order , @PathVariable long id){
            Response response = new Response();
            try {
                orderService.updateOrder(order, id);
                response.setStatusCode("200");
                response.setStatusMsg("Order is updated successfully into the databse");
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(response);
            }catch (Exception e){
                response.setStatusCode("400");
                response.setStatusMsg("Sorry ! No such an order to be updated. ");
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(response);
            }
    }

    @GetMapping("/getOrdersAfter")
    public List<Order> getOrdersAfterGivenDate(@RequestBody Order order){
        if(order!=null && order.getCreateDate()!=null){
            return orderService.getAllOrdersAccordingToDate(order.getCreateDate());
        }
        else{
            return null;
        }
    }





}