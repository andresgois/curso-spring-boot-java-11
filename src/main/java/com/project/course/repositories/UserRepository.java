package com.project.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
