package com.products.productdetails.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.products.productdetails.model.Book;
import com.products.productdetails.repository.BookRepository;

@Service
public class Book{
    @Autowired
    private BookRepository br;

    public List<Book> getAllBooks(){
            return br.findAll();
    }

    public String createBook(Book b){
        br.save(b);
        return "Book added successfully";
    }

    public String deleteBook(int id){
        br.deleteById(id);
        
        return "Book Removed Successfully";
    }
}