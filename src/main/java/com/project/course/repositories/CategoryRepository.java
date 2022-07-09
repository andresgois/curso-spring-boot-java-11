package com.project.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.course.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
