package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();

    @PostConstruct
    public void init() {
        // Dữ liệu mẫu như trong ảnh
        products.add(new Product(1, "LP001", "laptop 1", 30000, "laptop1.jpg", "Laptop"));
        products.add(new Product(2, "DT001", "dien thoai 1", 20000, "dienthoai1.jpg", "Điện thoại"));
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public Product getProductById(int id) {
        Optional<Product> product = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
        return product.orElse(null);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void updateProduct(int id, Product updatedProduct) {
        Optional<Product> existingProduct = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setProductCode(updatedProduct.getProductCode());
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            product.setImageName(updatedProduct.getImageName());
            product.setCategory(updatedProduct.getCategory());
        }
    }

    public void deleteProduct(int id) {
        products.removeIf(p -> p.getId() == id);
    }
}
