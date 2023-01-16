package com.EnocaIsYeriChall.EnocaIsYeriChall.Repository;

import com.EnocaIsYeriChall.EnocaIsYeriChall.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query("SELECT c FROM Customer c inner join c.orders o where c.name like %:customer_name%")
    List<Customer> findByPartOfName(@Param("customer_name") String customer_name);

    @Query("SELECT c FROM Customer c left join c.orders o where o.customer.id is null")
    List<Customer> getAllCustomersWithoutOrder();

}
