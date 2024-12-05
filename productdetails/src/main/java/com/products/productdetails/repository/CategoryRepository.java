package com.products.productdetails.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.products.productdetails.model.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

    
} 