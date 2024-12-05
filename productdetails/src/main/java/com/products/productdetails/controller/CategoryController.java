package com.products.productdetails.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.products.productdetails.model.Category;
//import com.products.productdetails.repository.CategoryRepository;
import com.products.productdetails.service.CategoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/Category")
public class CategoryController {
    @Autowired
    private CategoryService cs;

    @GetMapping("showCategory")
    public List<Category> showCategory() {
        return cs.getCategory();
    }

    @PostMapping("addCategory")
    public String addCategory(@RequestBody Category entity) {
       
        return cs.addCategoryService(entity);
    }
    
    @DeleteMapping("deleteCategory")
    public String deleteCategory(@RequestParam("C_ID") int c_id){
        return cs.deleteCategoryService(c_id);
    }
    
}
