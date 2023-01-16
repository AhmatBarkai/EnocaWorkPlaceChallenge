package com.EnocaIsYeriChall.EnocaIsYeriChall.Service;

import com.EnocaIsYeriChall.EnocaIsYeriChall.Entity.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    Order saveOrder(Order order);
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    void deleteOrder(Long id);
    Order updateOrder(Order order,Long id);

    List<Order> getAllOrdersAccordingToDate(LocalDate enteredDate);




}
