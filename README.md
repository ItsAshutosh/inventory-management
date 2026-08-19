# 📦 Inventory Management REST API

![Java 21](https://img.shields.io/badge/Java-21-orange.svg?style=for-the-badge&logo=openjdk)
![Spring Boot 3](https://img.shields.io/badge/Spring%20Boot-3.5-brightgreen.svg?style=for-the-badge&logo=springboot)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6.x-green.svg?style=for-the-badge&logo=springsecurity)
![JWT](https://img.shields.io/badge/JWT-JJWT_0.12.5-black.svg?style=for-the-badge&logo=jsonwebtokens)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg?style=for-the-badge&logo=mysql)
![Swagger UI](https://img.shields.io/badge/OpenAPI-Swagger_UI-green.svg?style=for-the-badge&logo=swagger)

A production-ready **Inventory Management Backend System** built with **Java 21**, **Spring Boot 3**, and **Spring Security**. Featuring stateless **JWT Authentication**, **Role-Based Access Control (RBAC)**, **Pagination & Sorting**, **Low Stock Alerts**, and **Automated Global Exception Handling**.

---

## 🚀 Live Demo & API Documentation

- **API Base URL:** `https://your-deployed-app.onrender.com/` *(Returns API Health & Status)*
- **Interactive Swagger UI:** `https://your-deployed-app.onrender.com/swagger-ui.html`

---

## ✨ Key Features

- 🔐 **Stateless JWT Authentication:** Secure login and registration with BCrypt password encoding and JWT token generation.
- 👥 **Role-Based Access Control (RBAC):** Fine-grained permission system differentiating `ROLE_USER` and `ROLE_ADMIN` using `@PreAuthorize`.
- 📦 **Advanced Product Management:**
  - Full CRUD operations for products.
  - Server-side pagination & dynamic sorting (`page`, `size`, `sortBy`, `sortDir`).
  - Real-time product search by name with paginated results.
  - Patch endpoint for atomic stock inventory updates (`PATCH /products/{id}/stock`).
  - **Low Stock Notification System:** Dedicated alert endpoint for items with quantity below threshold (`GET /products/low-stock`).
- 🏷️ **Category Management:** Categorize products with relational database integrity (`Category` -> `Product` One-To-Many relationship).
- 🛠️ **Global Exception Handling:** Centralized `@RestControllerAdvice` delivering clean, standardized JSON error messages for validation failures, duplicate resources, and invalid credentials.
- 📖 **OpenAPI 3.0 / Swagger UI:** Auto-generated interactive API documentation testable directly from any browser.
- ☁️ **Cloud Native:** Ready for one-click deployment on platforms like Render, Railway, AWS, or Docker.

---

## 🛠️ Tech Stack & Architecture

- **Language:** Java 21
- **Framework:** Spring Boot 3.5
- **Security:** Spring Security 6, JJWT 0.12.5 (JSON Web Token)
- **Persistence:** Spring Data JPA, Hibernate
- **Database:** MySQL 8.0 / H2 Database
- **Validation:** Jakarta Validation (`@Valid`, `@NotNull`, `@Min`, `@Email`)
- **Documentation:** Springdoc OpenAPI / Swagger UI
- **Build Tool:** Apache Maven

---

## 📑 API Endpoints Reference

### 🟢 Public Endpoints
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/` | API Root Health & Endpoints Status |
| `GET` | `/swagger-ui.html` | Interactive Swagger UI API Docs |
| `POST` | `/auth/register` | Register a new user (`ROLE_USER` or `ROLE_ADMIN`) |
| `POST` | `/auth/login` | Authenticate user & receive JWT Bearer Token |

### 🔵 Protected Endpoints (Requires `Bearer <JWT_TOKEN>`)
| Method | Endpoint | Required Role | Description |
| :--- | :--- | :--- | :--- |
| `GET` | `/categories` | `USER` / `ADMIN` | Fetch all categories |
| `GET` | `/categories/{id}` | `USER` / `ADMIN` | Fetch category by ID |
| `POST` | `/categories` | `ADMIN` | Create a new category |
| `PUT` | `/categories/{id}` | `ADMIN` | Update an existing category |
| `DELETE` | `/categories/{id}` | `ADMIN` | Delete category by ID |
| `GET` | `/products` | `USER` / `ADMIN` | Paginated list of products (`?page=0&size=10&sortBy=price&sortDir=asc`) |
| `GET` | `/products/{id}` | `USER` / `ADMIN` | Fetch product by ID |
| `GET` | `/products/search` | `USER` / `ADMIN` | Search products by name (`?name=laptop&page=0&size=10`) |
| `POST` | `/products` | `ADMIN` | Create product linked to a Category |
| `PUT` | `/products/{id}` | `ADMIN` | Update product details |
| `DELETE` | `/products/{id}` | `ADMIN` | Delete product by ID |
| `GET` | `/products/low-stock` | `ADMIN` | Retrieve all products with low stock count |
| `PATCH` | `/products/{id}/stock` | `ADMIN` | Update stock quantity (`?quantity=150`) |

---

## 💻 Local Setup & Run

### Prerequisites
- **JDK 21** installed (`java -version`)
- **Maven 3.8+** installed (`mvn -version`)
- **MySQL 8.0** running locally on port `3306`

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/inventory-management.git
cd inventory-management/rewrite
```

### 2. Configure Database
Create a MySQL database named `inventory_db`:
```sql
CREATE DATABASE inventory_db;
```

Update your credentials in `src/main/resources/application.properties` (or set environment variables):
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/inventory_db
spring.datasource.username=root
spring.datasource.password=your_password
```

### 3. Build & Run
```bash
mvn clean package
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

---

## ☁️ Free Cloud Deployment Guide (Render.com)

1. Push your project to **GitHub**.
2. Log in to [Render.com](https://render.com) and click **New + -> Web Service**.
3. Connect your GitHub repository.
4. Set up the deployment settings:
   - **Environment:** `Java` or `Docker`
   - **Build Command:** `./mvnw clean package -DskipTests`
   - **Start Command:** `java -jar target/inventory-management-0.0.1-SNAPSHOT.jar`
5. Add Environment Variables:
   - `SPRING_DATASOURCE_URL`: `jdbc:mysql://your-cloud-db-host:3306/your_db`
   - `SPRING_DATASOURCE_USERNAME`: `your_user`
   - `SPRING_DATASOURCE_PASSWORD`: `your_password`
   - `JWT_SECRET`: `yourSuperSecretKeyThatIsAtLeast32CharactersLong!`
6. Click **Deploy Web Service**. Once built, open your live URL in a browser!

---

## 🧪 Testing with Postman / cURL

### 1. Register User
```bash
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{"name":"John Doe", "email":"john@example.com", "password":"password123", "role":"ADMIN"}'
```

### 2. Login & Get Token
```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"john@example.com", "password":"password123"}'
```

### 3. Fetch Products (Using Token)
```bash
curl -X GET http://localhost:8080/products \
  -H "Authorization: Bearer <YOUR_JWT_TOKEN>"
```

---

## 📄 License
This project is open-source and available under the [MIT License](LICENSE).
