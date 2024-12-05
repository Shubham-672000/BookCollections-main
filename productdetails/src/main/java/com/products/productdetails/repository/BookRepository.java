package com.products.productdetails.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.products.productdetails.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    
}
