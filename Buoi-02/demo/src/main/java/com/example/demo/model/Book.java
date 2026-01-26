// src/main/java/com/example/demo/model/Book.java
package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Book {
    private Integer id;
    private String title;
    private String author;
}