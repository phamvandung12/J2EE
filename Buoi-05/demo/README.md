# Ứng dụng Quản lý Sản phẩm - Buoi-05

## 📋 Mô tả

Ứng dụng web quản lý sản phẩm được phát triển với Spring Boot và MySQL, sử dụng JPA (Hibernate) để kết nối cơ sở dữ liệu. Ứng dụng có các chức năng:
- ✅ Xem danh sách sản phẩm
- ✅ Thêm sản phẩm mới
- ✅ Sửa thông tin sản phẩm
- ✅ Xóa sản phẩm
- ✅ Upload hình ảnh sản phẩm
- ✅ Quản lý danh mục sản phẩm

## 🏗️ Kiến trúc ứng dụng

Ứng dụng tuân theo kiến trúc MVC với Spring Boot:

```
com.example.demo/
├── model/              # Entity classes (Product, Category)
│   ├── Product.java
│   └── Category.java
├── repository/         # JPA Repository interfaces
│   ├── ProductRepository.java
│   └── CategoryRepository.java
├── service/           # Business logic
│   ├── ProductService.java
│   └── CategoryService.java
└── controller/        # Controllers xử lý HTTP requests
    ├── ProductController.java
    └── HomeController.java
```

## 🛠️ Công nghệ sử dụng

- **Spring Boot 4.0.2**
  - Spring Web MVC
  - Spring Data JPA
  - Spring Boot DevTools
  - Spring Boot Validation
- **Thymeleaf** - Template engine
- **MySQL** - Database
- **Hibernate** - ORM
- **Lombok** - Giảm boilerplate code
- **Maven** - Build tool

## ⚙️ Cài đặt và Chạy

### Yêu cầu hệ thống
- Java 17 hoặc cao hơn
- MySQL 8.0+
- Maven 3.6+

### Bước 1: Chuẩn bị Database

1. Khởi động MySQL server
2. Tạo database:
```sql
CREATE DATABASE IF NOT EXISTS j2ee;
```

### Bước 2: Cấu hình

Kiểm tra file `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/j2ee
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# File Upload Configuration
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
```

⚠️ **Lưu ý:** Thay đổi `username` và `password` phù hợp với cấu hình MySQL của bạn.

### Bước 3: Build và Chạy

#### Sử dụng Maven Wrapper (khuyến nghị):
```bash
# Trên macOS/Linux
./mvnw clean install
./mvnw spring-boot:run

# Trên Windows
mvnw.cmd clean install
mvnw.cmd spring-boot:run
```

#### Hoặc sử dụng Maven:
```bash
mvn clean install
mvn spring-boot:run
```

### Bước 4: Truy cập ứng dụng

Mở trình duyệt và truy cập:
```
http://localhost:8080
```

Ứng dụng sẽ tự động chuyển hướng đến trang danh sách sản phẩm.

## 📦 Cấu trúc Database

### Bảng categories
```sql
CREATE TABLE categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);
```

### Bảng products
```sql
CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_code VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    price INT NOT NULL,
    image_name VARCHAR(255),
    category_id INT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);
```

## 🎯 Chức năng chính

### 1. Xem danh sách sản phẩm
- URL: `/products`
- Hiển thị tất cả sản phẩm với thông tin: tên, giá, hình ảnh, danh mục
- Các nút thao tác: Sửa, Xóa

### 2. Thêm sản phẩm mới
- URL: `/products/add`
- Form nhập: Mã SP, Tên, Giá, Danh mục, Hình ảnh
- Validation:
  - Mã sản phẩm không được để trống
  - Tên sản phẩm không được để trống
  - Giá từ 1 đến 9,999,999
  - Danh mục bắt buộc chọn

### 3. Sửa sản phẩm
- URL: `/products/edit/{id}`
- Cho phép cập nhật thông tin sản phẩm
- Có thể giữ nguyên hình ảnh cũ hoặc upload ảnh mới

### 4. Xóa sản phẩm
- URL: `/products/delete/{id}`
- Có xác nhận trước khi xóa

## 📂 Upload File

Hình ảnh sản phẩm được lưu tại:
```
src/main/resources/static/uploads/
```

Định dạng hỗ trợ: JPG, PNG, GIF

## 🔧 Troubleshooting

### Lỗi kết nối MySQL
```
Error: Communications link failure
```
**Giải pháp:**
- Kiểm tra MySQL đã chạy: `mysql -u root -p`
- Xác nhận database `j2ee` đã tồn tại
- Kiểm tra username/password trong `application.properties`

### Lỗi build Maven
```
Error: 'dependencies.dependency.version' is missing
```
**Giải pháp:** Đã được fix bằng cách sử dụng `com.mysql:mysql-connector-j` thay vì `mysql:mysql-connector-java`

### Lỗi upload file
```
Error: Maximum upload size exceeded
```
**Giải pháp:** Tăng giới hạn trong `application.properties`:
```properties
spring.servlet.multipart.max-file-size=20MB
spring.servlet.multipart.max-request-size=20MB
```

## 📝 Dữ liệu mẫu

Khi khởi động lần đầu, ứng dụng tự động tạo dữ liệu mẫu:

**Categories:**
- Laptop
- Điện thoại

**Products:**
1. Lenovo ThinkPad T15 15.6" Laptop Intel Core i7-10610U 512GB SSD 16GB RAM FHD - 27,000 đ (Laptop)
2. iPhone 16 Pro Max 1TB - 41,990 đ (Điện thoại)

## 👨‍💻 Phát triển thêm

### Thêm danh mục mới
Chỉnh sửa `CategoryService.java`:
```java
@PostConstruct
public void init() {
    if (categoryRepository.count() == 0) {
        categoryRepository.save(new Category(null, "Laptop"));
        categoryRepository.save(new Category(null, "Điện thoại"));
        categoryRepository.save(new Category(null, "Tablet")); // Thêm mới
    }
}
```

### Thêm validation
Bổ sung annotation trong `Product.java`:
```java
@Pattern(regexp = "^[A-Z]{2}\\d{3}$", message = "Mã sản phẩm phải theo format: 2 chữ cái + 3 số")
private String productCode;
```

## 📚 Tài liệu tham khảo

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Thymeleaf](https://www.thymeleaf.org/)
- [MySQL Connector/J](https://dev.mysql.com/doc/connector-j/8.0/en/)

## 📄 License

Dự án học tập - J2EE Course

---
© 2025 - Phát triển ứng dụng với J2EE
