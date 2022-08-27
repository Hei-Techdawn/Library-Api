package com.example.libraryapi.service;

import com.example.libraryapi.model.Category;
import com.example.libraryapi.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;

    public DataFormat<Category> getAll() {
        DataFormat<Category> dataFormat = new DataFormat<>();
        dataFormat.setData(categoryRepository.findAll());
        dataFormat.setLastPage(0);
        dataFormat.setCurrentPage(0);
        return dataFormat;
    }

    public Category getById(Long id) {
        return categoryRepository.findById(id).get();
    }

    public Category putById(Long id, Category category) {
        Category oldCategory = categoryRepository.findById(id).get();
        if (category.getName() != null) {
            oldCategory.setName(category.getName());
        }
        if (category.getDescription() != null) {
            oldCategory.setDescription(category.getDescription());
        }
        return categoryRepository.save(oldCategory);
    }

    @Transactional
    public List<Category> saveAll(List<Category> categoryList) {
        return categoryRepository.saveAll(categoryList);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
