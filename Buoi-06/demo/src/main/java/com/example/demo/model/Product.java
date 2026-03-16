package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message = "Mã sản phẩm không được để trống")
    @Column(name = "product_code", nullable = false, unique = true)
    private String productCode;
    
    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Column(nullable = false)
    private String name;
    
    @NotNull(message = "Giá sản phẩm không được để trống")
    @Min(value = 1, message = "Giá sản phẩm phải từ 1 đến 9999999")
    @Max(value = 9999999, message = "Giá sản phẩm phải từ 1 đến 9999999")
    @Column(nullable = false)
    private Integer price;
    
    @Column(name = "image_name")
    private String imageName;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    @NotNull(message = "Danh mục không được để trống")
    private Category category;
}
