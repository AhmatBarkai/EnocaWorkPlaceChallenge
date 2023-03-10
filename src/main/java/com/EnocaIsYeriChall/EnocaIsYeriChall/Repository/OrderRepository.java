package com.EnocaIsYeriChall.EnocaIsYeriChall.Repository;

import com.EnocaIsYeriChall.EnocaIsYeriChall.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("select o from Order o where o.createDate > :#{#enteredDate}")
    List<Order> findByGivenDate(@Param("enteredDate") LocalDate enteredDate);

}
