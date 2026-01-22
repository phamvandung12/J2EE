package Buoi_01;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author phamvandung
 */
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Book> listBook = new ArrayList<>();
        Scanner x = new Scanner(System.in);
        
        String menu = """
            \n--- CHƯƠNG TRÌNH QUẢN LÝ SÁCH ---
            1. Thêm 1 cuốn sách
            2. Xóa 1 cuốn sách
            3. Thay đổi cuốn sách
            4. Xuất thông tin tất cả cuốn sách
            5. Tìm sách có tựa đề chứa "Lập trình" (không phân biệt hoa thường)
            6. Lấy tối đa K cuốn sách có giá <= P
            7. Tìm sách theo danh sách tác giả
            0. Thoát
            Chọn chức năng: """;

        int chon = -1;
        do {
            System.out.print(menu);
            try {
                chon = Integer.parseInt(x.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số!");
                continue;
            }

            // Sử dụng Switch Expression (Java 12+)
            switch (chon) {
                case 1 -> {
                    Book newBook = new Book();
                    newBook.input();
                    listBook.add(newBook);
                    System.out.println("Thêm thành công!");
                }
                case 2 -> {
                    System.out.print("Nhập mã sách cần xóa: ");
                    int bookId = Integer.parseInt(x.nextLine());
                    boolean removed = listBook.removeIf(p -> p.getId() == bookId);
                    if (removed) System.out.println("Đã xóa thành công!");
                    else System.out.println("Không tìm thấy mã sách.");
                }
                case 3 -> {
                    System.out.print("Nhập mã sách cần điều chỉnh: ");
                    int bookId = Integer.parseInt(x.nextLine());
                    listBook.stream()
                            .filter(p -> p.getId() == bookId)
                            .findFirst()
                            .ifPresentOrElse(
                                b -> { b.input(); System.out.println("Cập nhật thành công!"); },
                                () -> System.out.println("Không tìm thấy sách!")
                            );
                }
                case 4 -> {
                    System.out.println("\n--- DANH SÁCH SÁCH ---");
                    listBook.forEach(Book::output);
                }
                case 5 -> {
                    System.out.println("\n--- KẾT QUẢ TÌM KIẾM ('Lập trình') ---");
                    listBook.stream()
                            .filter(u -> u.getTitle().toLowerCase().contains("lập trình"))
                            .forEach(Book::output);
                }
                case 6 -> {
                    System.out.print("Nhập số lượng tối đa K: ");
                    int k = Integer.parseInt(x.nextLine());
                    System.out.print("Nhập mức giá P tối đa: ");
                    double pPrice = Double.parseDouble(x.nextLine());
                    
                    listBook.stream()
                            .filter(b -> b.getPrice() <= pPrice)
                            .limit(k)
                            .forEach(Book::output);
                }
                case 7 -> {
                    System.out.print("Nhập danh sách tác giả (cách nhau bởi dấu phẩy): ");
                    String inputAuthors = x.nextLine();
                    Set<String> authorSet = Arrays.stream(inputAuthors.split(","))
                                                  .map(String::trim)
                                                  .collect(Collectors.toSet());
                    
                    listBook.stream()
                            .filter(b -> authorSet.contains(b.getAuthor()))
                            .forEach(Book::output);
                }
                case 0 -> System.out.println("Tạm biệt!");
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (chon != 0);
    }
}
