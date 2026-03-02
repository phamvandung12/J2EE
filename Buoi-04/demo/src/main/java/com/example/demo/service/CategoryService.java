package com.example.demo.service;

import com.example.demo.model.Category;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private List<Category> categories = new ArrayList<>();

    @PostConstruct
    public void init() {
        // Dữ liệu mẫu categories
        categories.add(new Category(1, "Laptop"));
        categories.add(new Category(2, "Điện thoại"));
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>(categories);
    }

    public Category getCategoryById(int id) {
        Optional<Category> category = categories.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
        return category.orElse(null);
    }

    public Category getCategoryByName(String name) {
        Optional<Category> category = categories.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst();
        return category.orElse(null);
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void updateCategory(int id, Category updatedCategory) {
        Optional<Category> existingCategory = categories.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
        if (existingCategory.isPresent()) {
            Category category = existingCategory.get();
            category.setName(updatedCategory.getName());
        }
    }

    public void deleteCategory(int id) {
        categories.removeIf(c -> c.getId() == id);
    }
}
