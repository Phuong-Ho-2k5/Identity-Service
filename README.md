# Identity Service

Service viết bằng Java 21 và Spring Boot 4.1.2, dùng Spring Data JPA để làm việc với PostgreSQL.

Project hiện có API tạo, xem, cập nhật và xóa người dùng. Chưa có chức năng đăng nhập, xác thực hoặc phân quyền.

## Chạy ở local

Cần cài JDK 21 và PostgreSQL. Project có sẵn Maven Wrapper nên không cần cài Maven riêng.

Tạo database:

```sql
CREATE DATABASE identity_service;
```

Sau đó mở terminal tại thư mục project và cấu hình kết nối. Thay username và password bên dưới bằng tài khoản PostgreSQL của bạn.

Cấu hình database chỉ đọc từ ba biến môi trường bên dưới; cần thiết lập đủ trước khi chạy. Không ghi mật khẩu thật vào source code hoặc commit lên Git. Spring Boot không tự động nạp file `.env`; các lệnh dưới thiết lập biến trực tiếp cho terminal.

File [`.env.example`](.env.example) chứa mẫu cấu hình. Có thể sao chép thành `.env` và điền thông tin local để dùng với IDE hoặc công cụ nạp biến môi trường. File `.env` được Git bỏ qua; chỉ tạo file này chưa đủ để ứng dụng đọc cấu hình.

### Windows (PowerShell)

```powershell
$env:SPRING_DATASOURCE_URL = "jdbc:postgresql://localhost:5432/identity_service"
$env:SPRING_DATASOURCE_USERNAME = "your_username"
$env:SPRING_DATASOURCE_PASSWORD = "your_password"

.\mvnw.cmd spring-boot:run
```

### Linux / macOS

```bash
export SPRING_DATASOURCE_URL='jdbc:postgresql://localhost:5432/identity_service'
export SPRING_DATASOURCE_USERNAME='your_username'
export SPRING_DATASOURCE_PASSWORD='your_password'

./mvnw spring-boot:run
```

Nếu gặp lỗi `Permission denied`, chạy `chmod +x mvnw` rồi thử lại.

Ứng dụng chạy tại `http://localhost:8080/identity`. API người dùng nằm tại `/identity/users`; chưa có endpoint cho đường dẫn gốc `/identity`, nên truy cập đường dẫn này sẽ trả về `404`.

Các biến môi trường trên chỉ áp dụng cho phiên terminal hiện tại. Khi mở terminal mới để chạy app, test hoặc build, cần thiết lập lại.

## API người dùng

| Method | Đường dẫn | Chức năng |
| --- | --- | --- |
| POST | `/identity/users` | Tạo người dùng |
| GET | `/identity/users` | Lấy danh sách người dùng |
| GET | `/identity/users/{userId}` | Lấy thông tin một người dùng |
| PUT | `/identity/users/{userId}` | Cập nhật người dùng |
| DELETE | `/identity/users/{userId}` | Xóa người dùng |

Body JSON khi tạo người dùng gồm `username`, `password`, `firstName`, `lastName`, `dob`. Khi cập nhật, body gồm `password`, `firstName`, `lastName`, `dob`. Trường `dob` dùng định dạng `YYYY-MM-DD`.

## Test và build

Trên PowerShell:

```powershell
# Chạy test
.\mvnw.cmd test

# Đóng gói
.\mvnw.cmd clean package

# Chạy file JAR
java -jar target/identity-service-0.0.1-SNAPSHOT.jar
```

Trên Linux / macOS, thay `.\mvnw.cmd` bằng `./mvnw`.

Test hiện tại chỉ kiểm tra Spring context có khởi tạo được hay không và cần kết nối PostgreSQL. Lệnh `package` cũng chạy test, nên database cần sẵn sàng khi build.

## Mã nguồn

- [`pom.xml`](pom.xml): dependencies và cấu hình build.
- [`src/main/java/com/identity/identity_service/`](src/main/java/com/identity/identity_service/): mã nguồn ứng dụng.
- [`application.yaml`](src/main/resources/application.yaml): cấu hình Spring Boot; thông tin kết nối database lấy từ biến môi trường.
- [`.env.example`](.env.example): mẫu biến môi trường cho database, không chứa thông tin đăng nhập thật.
- [`src/test/java/com/identity/identity_service/`](src/test/java/com/identity/identity_service/): mã kiểm thử.
