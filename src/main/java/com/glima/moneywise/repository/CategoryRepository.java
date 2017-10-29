package com.glima.moneywise.repository;

import com.glima.moneywise.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
