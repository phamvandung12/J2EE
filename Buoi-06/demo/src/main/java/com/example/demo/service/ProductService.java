package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.List;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CategoryService categoryService;

    @PostConstruct
    public void init() {
        // Khởi tạo dữ liệu mẫu nếu database trống
        if (productRepository.count() == 0) {
            Category laptop = categoryService.getCategoryByName("Laptop");
            Category phone = categoryService.getCategoryByName("Điện thoại");
            
            if (laptop != null) {
                productRepository.save(new Product(null, "LP001", 
                    "Lenovo ThinkPad T15 15.6\" Laptop Intel Core i7-10610U 512GB SSD 16GB RAM FHD", 
                    27000, "laptop1.jpg", laptop));
            }
            
            if (phone != null) {
                productRepository.save(new Product(null, "DT001", 
                    "iPhone 16 Pro Max 1TB", 
                    41990, "phone1.jpg", phone));
            }
        }
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Integer id, Product updatedProduct) {
        if (productRepository.existsById(id)) {
            updatedProduct.setId(id);
            return productRepository.save(updatedProduct);
        }
        return null;
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
}
