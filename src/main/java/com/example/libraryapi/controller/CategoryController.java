package com.example.libraryapi.controller;

import com.example.libraryapi.model.Category;
import com.example.libraryapi.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/category")
@CrossOrigin(origins = "*")
public class CategoryController {
    private CategoryService categoryService;

    @GetMapping(value = "")
    public List<Category> getCategory() {
        return categoryService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @PutMapping(value = "/{id}")
    public Category putCategoryById(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.putById(id, category);
    }

    @PostMapping(value = "")
    public List<Category> saveCategory(@RequestBody List<Category> categoryList) {
        return categoryService.saveAll(categoryList);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
