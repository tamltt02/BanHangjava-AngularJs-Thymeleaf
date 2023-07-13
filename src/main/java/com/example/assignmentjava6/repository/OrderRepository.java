package com.example.assignmentjava6.repository;

import com.example.assignmentjava6.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
  @Query("select o from Order o where o.account.username = ?1")
    List<Order> findByUsername(String username);


}

