package com.project.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.course.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
