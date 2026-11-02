# Identity Service

Service viết bằng Java 21 và Spring Boot 4.1.2, dùng Spring Data JPA để làm việc với PostgreSQL.

Project hiện mới có phần khởi tạo, chưa có API đăng ký hay đăng nhập.

## Chạy ở local

Cần cài JDK 21 và PostgreSQL. Project có sẵn Maven Wrapper nên không cần cài Maven riêng.

Tạo database:

```sql
CREATE DATABASE identity_service;
```

Sau đó mở terminal tại thư mục project và cấu hình kết nối. Thay username và password bên dưới bằng tài khoản PostgreSQL của bạn.

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

Ứng dụng chạy tại `http://localhost:8080`. Hiện chưa có controller nên truy cập đường dẫn `/` sẽ trả về `404`.

Các biến môi trường trên chỉ áp dụng cho phiên terminal hiện tại. Khi mở terminal mới để chạy app, test hoặc build, cần thiết lập lại.

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
- [`application.yaml`](src/main/resources/application.yaml): cấu hình Spring Boot; hiện chỉ có tên ứng dụng.
- [`src/test/java/com/identity/identity_service/`](src/test/java/com/identity/identity_service/): mã kiểm thử.
