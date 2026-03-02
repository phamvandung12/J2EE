package com.example.demo.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Product {
    private Integer id;
    
    @NotBlank(message = "Mã sản phẩm không được để trống")
    private String productCode;
    
    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String name;
    
    @NotNull(message = "Giá sản phẩm không được để trống")
    @Min(value = 1, message = "Giá sản phẩm phải từ 1 đến 9999999")
    @Max(value = 9999999, message = "Giá sản phẩm phải từ 1 đến 9999999")
    private Integer price;
    
    private String imageName;
    
    @NotBlank(message = "Danh mục không được để trống")
    private String category;
}
