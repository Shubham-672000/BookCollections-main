package com.products.productdetails.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;

import com.products.productdetails.model.Book;
//import com.products.productdetails.service.CategoryService;

//import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/myBookController")
public class BookController {
	
	@PersistenceContext
    private EntityManager entityManager;
 
    @GetMapping("/book")
    public PaginatedResponse<Book> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
 
        // Fetch the total count of records
        long totalRecords = (long) entityManager.createQuery("SELECT COUNT(s) FROM Book s")
                .getSingleResult();
 
        // Fetch paginated results
        TypedQuery<Book> query = entityManager.createQuery("SELECT s FROM Book s", Book.class);
        query.setFirstResult(page * size); // Starting index
        query.setMaxResults(size);        // Page size
 
        List<Book> books = query.getResultList();
 
        // Return paginated response
        return new PaginatedResponse<>(books, page, size, totalRecords);
    }
    
    @PostMapping("addBook")
    public String createBook(@RequestBody Book b) { 
        // List<Category> categoryList = css.getCategory();
        return bs.createBook(b);
    }

    @DeleteMapping("deleteBook")
    public String deleteBook(@RequestParam("ID") int id){
        return br.deleteBook(id);
    }
 
    // Inner class for paginated response
    public static class PaginatedResponse<T> {
        private List<T> content;
        private int currentPage;
        private int pageSize;
        private long totalRecords;
        private int totalPages;
 
        public PaginatedResponse(List<T> content, int currentPage, int pageSize, long totalRecords) {
            this.content = content;
            this.currentPage = currentPage;
            this.pageSize = pageSize;
            this.totalRecords = totalRecords;
            this.totalPages = (int) Math.ceil((double) totalRecords / pageSize);
        }
 
        // Getters and setters
        public List<T> getContent() {
            return content;
        }
 
        public void setContent(List<T> content) {
            this.content = content;
        }
 
        public int getCurrentPage() {
            return currentPage;
        }
 
        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }
 
        public int getPageSize() {
            return pageSize;
        }
 
        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }
 
        public long getTotalRecords() {
            return totalRecords;
        }
 
        public void setTotalRecords(long totalRecords) {
            this.totalRecords = totalRecords;
        }
 
        public int getTotalPages() {
            return totalPages;
        }
 
        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }
    }
    
    

}
