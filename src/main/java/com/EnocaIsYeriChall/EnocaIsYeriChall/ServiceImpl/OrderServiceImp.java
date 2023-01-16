package com.EnocaIsYeriChall.EnocaIsYeriChall.ServiceImpl;

import com.EnocaIsYeriChall.EnocaIsYeriChall.Entity.Order;
import com.EnocaIsYeriChall.EnocaIsYeriChall.Repository.OrderRepository;
import com.EnocaIsYeriChall.EnocaIsYeriChall.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderRepository orderRepository;


    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);

    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public void deleteOrder(Long id) {

        Order isPresent = orderRepository.getById(id);
        if (isPresent != null){
            orderRepository.deleteById(id);
        }

    }

    @Override
    public Order updateOrder(Order order, Long id) {
        Order isPresentOrder = orderRepository.getById(id);
        if(isPresentOrder!=null){
            isPresentOrder.setTotalPrice(order.getTotalPrice());
            orderRepository.save(isPresentOrder);
        }
       return isPresentOrder;
    }

    @Override
    public List<Order> getAllOrdersAccordingToDate(LocalDate enteredDate) {
        return orderRepository.findByGivenDate(enteredDate);
    }


}
