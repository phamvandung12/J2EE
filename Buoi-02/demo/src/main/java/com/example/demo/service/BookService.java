// src/main/java/com/example/demo/service/BookService.java
package com.example.demo.service;

import com.example.demo.model.Book;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private List<Book> books = new ArrayList<>();

    @PostConstruct
    public void init() {
        books.add(new Book(1, "Dế Mèn Phiêu Lưu Ký", "Tô Hoài"));
        books.add(new Book(2, "Số Đỏ", "Vũ Trọng Phụng"));
        books.add(new Book(3, "Lão Hạc", "Nam Cao"));
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public Book getBookById(int id) {
        Optional<Book> book = books.stream().filter(b -> b.getId() == id).findFirst();
        return book.orElse(null);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void updateBook(int id, Book updatedBook) {
        Optional<Book> existingBook = books.stream().filter(b -> b.getId() == id).findFirst();
        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
        }
    }

    public void deleteBook(int id) {
        books.removeIf(b -> b.getId() == id);
    }
}