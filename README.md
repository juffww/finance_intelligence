# Finance Intelligence App

Một hệ thống backend quản lý tài chính cá nhân, danh mục tài sản và theo dõi thị trường. Dự án được thiết kế theo chuẩn **Clean Architecture** kết hợp mô hình **Modular Monolith with domain-driven design**, tập trung vào tính mở rộng, hiệu năng cao và dễ dàng bảo trì.

## Công nghệ sử dụng

* **Ngôn ngữ:** Java (JDK 17+)
* **Framework:** Spring Boot 3.x
* **Database:** PostgreSQL
* **Mapping:** MapStruct & Lombok
* **Build Tool:** Maven

## Kiến trúc hệ thống

Dự án áp dụng nguyên tắc **Clean Architecture** để phân tách rõ ràng ranh giới giữa nghiệp vụ và công nghệ:
* **`domain`:** Chứa cốt lõi nghiệp vụ (Entities, Repository Interfaces). Hoàn toàn là Java thuần, không phụ thuộc vào Framework hay cấu trúc Database.
* **`application`:** Chứa Use cases (Services), DTOs, Mappers và Ports (Interfaces giao tiếp).
* **`infrastructure`:** Chứa REST APIs (Controllers), JPA Entities, Cấu hình Spring và Triển khai Database Repository.

Các module giao tiếp với nhau theo nguyên lý **Decoupling** (giảm thiểu phụ thuộc), dữ liệu tham chiếu chéo được liên kết thông qua `UUID` (Khóa ngoại) thay vì sử dụng trực tiếp các Object Entity của nhau.

## Các Module chính

1.  **User Module:** Xử lý định danh, tài khoản người dùng và xác thực.
2.  **Market Data Module:** Cung cấp dữ liệu thị trường, định giá và thông tin chi tiết về tài sản (Tiền mã hóa, Cổ phiếu...).
3.  **Watchlist Module:** Đóng vai trò là Aggregator (Tổng hợp), cho phép người dùng tự tạo và quản lý danh sách theo dõi tài sản cá nhân. Giao tiếp với Asset Module thông qua các Port/Facade nội bộ để lấy dữ liệu chi tiết.

## Hướng dẫn cài đặt & Chạy dự án

### 1. Yêu cầu môi trường
* Java 17 trở lên
* PostgreSQL đã được cài đặt và đang chạy.

### 2. Cấu hình Database
Tạo một database trong PostgreSQL (ví dụ: `finance_db`) và cập nhật thông tin kết nối trong file `application.yml` (hoặc `application.properties`):

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/finance_db
    username: [your_username]
    password: [your_password]
  jpa:
    hibernate:
      ddl-auto: update # Hoặc validate/none tùy môi trường
