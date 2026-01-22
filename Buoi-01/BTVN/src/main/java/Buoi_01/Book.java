package Buoi_01;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author phamvandung
 */
import java.util.Scanner;

public class Book {
    private int id;
    private String title;
    private String author;
    private long price;

    // Constructors
    public Book() {}

    public Book(int id, String title, String author, long price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public long getPrice() { return price; }
    public void setPrice(long price) { this.price = price; }

    // Phương thức nhập thông tin
    public void input() {
        Scanner x = new Scanner(System.in);
        System.out.print("Nhập mã sách: ");
        this.id = Integer.parseInt(x.nextLine());
        System.out.print("Nhập tên sách: ");
        this.title = x.nextLine();
        System.out.print("Nhập tác giả: ");
        this.author = x.nextLine();
        System.out.print("Nhập đơn giá: ");
        this.price = x.nextLong();
    }

    // Phương thức xuất thông tin (Sử dụng Text Block và formatted)
    public void output() {
        String msg = """
                BOOK: id= %d, title=%s, author=%s, price=%d""";
        System.out.println(msg.formatted(id, title, author, price));
    }
}
