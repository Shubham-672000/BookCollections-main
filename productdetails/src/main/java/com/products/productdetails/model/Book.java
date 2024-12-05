package com.products.productdetails.model;



import com.fasterxml.jackson.annotation.JsonBackReference;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="BookTable")
public class Book{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name="ID")
    private int id;

    @Column(name="BOOKNAME")
    private String bookName;

    @Column(name="AUTHOR")
    private String authorName;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="C_ID")
    @JsonBackReference
    private Category category;

    

    public Book() {
    }

    

    public Book(int id, String bookName, String authorName, Category category) {
        this.id = id;
        this.bookName = bookName;
        this.authorName = authorName;
        this.category = category;
    }




    @Override
    public String toString() {
        return "Book [id=" + id + ", bookName=" + bookName + ", authorName=" + authorName + ", category=" + category
                + "]";
    }

    
}
