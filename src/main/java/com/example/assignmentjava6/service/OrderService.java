package com.example.assignmentjava6.service;

import com.example.assignmentjava6.entity.Order;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface OrderService {
    Order create(JsonNode orderData);

    Order findById(Long id);

    List<Order> findByUsername(String username);
}
