package com.products.productdetails.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.products.productdetails.model.Category;
import com.products.productdetails.repository.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository crs;

    public List<Category> getCategory(){
        return crs.findAll();
    }

    public String addCategoryService(Category entity){
        crs.save(entity);
        return "Added Category";
    }

    public String deleteCategoryService(int id){
        crs.deleteById(id);
        return "Removed Category";
    }

}
