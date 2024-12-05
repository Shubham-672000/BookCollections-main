package com.products.productdetails.model;

import java.util.ArrayList;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="CategoryTable")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int c_id;

    
    private String c_nmae;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "category")
    @JsonManagedReference
    private List<Book> bookList = new ArrayList<>();

    public Category(int c_id, String c_nmae, List<Book> bookList) {
        this.c_id = c_id;
        this.c_nmae = c_nmae;
        this.bookList = bookList;
    }

    public Category() {
    }

    @Override
    public String toString() {
        return "Category [c_id=" + c_id + ", c_nmae=" + c_nmae + ", bookList=" + bookList + "]";
    }
 
}
