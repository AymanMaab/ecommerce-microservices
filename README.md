# 🛒 E-Commerce Lite - Microservices Backend

A complete, production-ready microservices backend built with **Java 21**, **Spring Boot**, **MongoDB**, **Docker**, and **CI/CD**.

> **Perfect for learning**: This project teaches you microservices architecture, MongoDB aggregations, Docker, and CI/CD through a real-world e-commerce example!

## 📋 Table of Contents
- [System Architecture](#-system-architecture)
- [Technologies](#-technologies-used)
- [Quick Start](#-quick-start)
- [API Documentation](#-api-documentation)
- [MongoDB Aggregations](#-mongodb-aggregations)
- [Docker](#-docker-setup)
- [Beginner's Guide](#-beginners-guide)

---

## 🏗️ System Architecture

### The Big Picture

```
┌─────────────────┐     ┌──────────────────┐     ┌─────────────────┐
│  User Service   │     │  Product Service │     │  Order Service  │
│   Port: 8081    │     │   Port: 8082     │     │   Port: 8083    │
└────────┬────────┘     └────────┬─────────┘     └────────┬────────┘
         │                       │                        │
         └───────────────────────┴────────────────────────┘
                                 │
                      ┌──────────▼──────────┐
                      │   MongoDB Database  │
                      │    Port: 27017      │
                      └─────────────────────┘
```

**Think of it like a shopping mall:**
- 👤 **User Service** = Customer Service Desk (manages who you are)
- 📦 **Product Service** = Warehouse (what's available)
- 🛒 **Order Service** = Checkout Counter (processes purchases)

---

## 🛠️ Technologies Used

| Technology | Version | Purpose |
|------------|---------|---------|
| ☕ **Java** | 21 (LTS) | Programming Language |
| 🍃 **Spring Boot** | 3.2.0 | Application Framework |
| 📊 **MongoDB** | 7.0+ | NoSQL Database |
| 📦 **Maven** | 3.9+ | Build Tool |
| 📚 **Swagger** | 2.3.0 | API Documentation |
| 🐳 **Docker** | 24.0+ | Containerization |
| 🔄 **GitHub Actions** | - | CI/CD Pipeline |

---

## 🚀 Quick Start

### Prerequisites
```bash
# Verify you have these installed:
java -version    # Should show Java 21
mvn -version     # Should show Maven 3.9+
docker --version
```

### Run with Docker (Easiest!)

```bash
# 1. Clone the repo
git clone https://github.com/AymanMaab/ecommerce-microservices.git
cd ecommerce-microservices/demo

# 2. Start everything with one command!
docker-compose up -d

# 3. Check they're running
docker-compose ps

# 4. Open Swagger to test APIs
# User Service:    http://localhost:8081/swagger-ui.html
# Product Service: http://localhost:8082/swagger-ui.html
# Order Service:   http://localhost:8083/swagger-ui.html
```

### Run Locally (Without Docker)

```bash
# 1. Start MongoDB
mongod --dbpath /path/to/data/db

# 2. Build all services
mvn clean install

# 3. Run each service in separate terminals:
cd user-service && mvn spring-boot:run     # Terminal 1
cd product-service && mvn spring-boot:run  # Terminal 2
cd order-service && mvn spring-boot:run    # Terminal 3
```

---

## 📚 API Documentation

Each service has **interactive Swagger UI** where you can test APIs directly in your browser!

| Service | Swagger URL | What it does |
|---------|-------------|--------------|
| **User Service** | http://localhost:8081/swagger-ui.html | Manage customers |
| **Product Service** | http://localhost:8082/swagger-ui.html | Manage products |
| **Order Service** | http://localhost:8083/swagger-ui.html | Process orders |

### 🎮 Try it Yourself!

**Example: Create a User**

1. Go to http://localhost:8081/swagger-ui.html
2. Find `POST /api/users`
3. Click **"Try it out"**
4. Paste this JSON:
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "phone": "1234567890",
  "address": "123 Main St"
}
```
5. Click **"Execute"**
6. 🎉 You just created a user!

---

## 🔬 MongoDB Aggregations

The **Order Service** showcases advanced MongoDB aggregation pipelines.

### Example: Total Revenue by Product

**Endpoint:** `GET /api/orders/analytics/revenue`

**What it does:** Groups all orders and calculates how much revenue each product generated.

**MongoDB Pipeline:**
```javascript
[
  { $unwind: "$items" },                    // Separate each item
  { 
    $group: {
      _id: "$items.productId",
      totalRevenue: { $sum: "$items.price" }
    }
  },
  { $sort: { totalRevenue: -1 } }          // Sort by highest revenue
]
```

**Response:**
```json
[
  {
    "productId": "LAP-001",
    "totalRevenue": 15000.00,
    "totalQuantitySold": 15
  }
]
```

### Other Aggregations

- `GET /api/orders/analytics/top-customers` - Top 5 customers by order count
- `GET /api/orders/analytics/user-stats/{userId}` - Average order value per user
- `GET /api/orders/analytics/monthly-sales` - Sales trends by month

---

## 🐳 Docker Setup

### docker-compose.yml

```yaml
version: '3.8'

services:
  mongodb:
    image: mongo:7.0
    ports:
      - "27017:27017"
    volumes:
      - mongodb_data:/data/db

  user-service:
    build: ./user-service
    ports:
      - "8081:8081"
    environment:
      SPRING_DATA_MONGODB_URI: mongodb://mongodb:27017/ecommerce
    depends_on:
      - mongodb

  product-service:
    build: ./product-service
    ports:
      - "8082:8082"
    depends_on:
      - mongodb

  order-service:
    build: ./order-service
    ports:
      - "8083:8083"
    depends_on:
      - mongodb

volumes:
  mongodb_data:
```

### Dockerfile (Example)

```dockerfile
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

## 🎓 Beginner's Guide

### What is Dependency Injection?

**Real-life analogy:** Instead of making your own coffee (creating objects), a barista (Spring) makes it for you!

```java
// ❌ Without DI
public class UserService {
    private UserRepository repo = new UserRepository();  // You create it
}

// ✅ With DI
@Service
public class UserService {
    private final UserRepository repo;  // Spring injects it
    
    public UserService(UserRepository repo) {
        this.repo = repo;
    }
}
```

### DTOs vs Entities

- **Entity** = Your passport (complete official information)
- **DTO** = Boarding pass (only necessary info for this trip)

```java
// Entity (in database)
@Document
class User {
    String id;          // Auto-generated
    String password;    // Sensitive!
    LocalDateTime createdAt;
}

// Request DTO (from user)
class UserRequest {
    String name;
    String email;
    // No password or ID!
}

// Response DTO (to user)
class UserResponse {
    String id;
    String name;
    String email;
    // No password! (security)
}
```

### Layered Architecture

```
Controller  → Receives HTTP requests (receptionist)
    ↓
Service     → Business logic (manager)
    ↓
Repository  → Database access (file clerk)
    ↓
MongoDB     → Actual database
```

---

## 📁 Project Structure

```
ecommerce-microservices/
├── pom.xml                    # Parent POM
├── user-service/
│   ├── src/main/java/com/ecommerce/user/
│   │   ├── UserServiceApplication.java
│   │   ├── model/User.java
│   │   ├── dto/
│   │   ├── repository/UserRepository.java
│   │   ├── service/UserService.java
│   │   ├── controller/UserController.java
│   │   └── exception/
│   └── pom.xml
├── product-service/
├── order-service/
├── docker-compose.yml
└── README.md
```

---

## ⚙️ CI/CD Pipeline

GitHub Actions automatically:
✅ Builds the project  
✅ Runs tests  
✅ Creates Docker images  
✅ Pushes to Docker Hub (on main branch)

File: `.github/workflows/ci-cd.yml`

```yaml
name: CI/CD Pipeline

on:
  push:
    branches: [ main ]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK 21
        uses: actions/setup-java@v3
        with:
          java-version: '21'
      - name: Build with Maven
        run: mvn clean install
      - name: Build Docker Images
        run: docker-compose build
```

---

## 🎯 What You'll Learn

By exploring this project:

✅ Microservices architecture  
✅ Spring Boot best practices  
✅ MongoDB CRUD + Aggregations  
✅ RESTful API design  
✅ Dependency Injection  
✅ Exception handling  
✅ Swagger documentation  
✅ Docker containerization  
✅ CI/CD pipelines  

---

## 👨‍💻 Author

**Ayman Maab**
- GitHub: [@AymanMaab](https://github.com/AymanMaab)
- Repository: https://github.com/AymanMaab/ecommerce-microservices

---

## 📄 License

MIT License - feel free to use this for learning!

---

**Happy Coding! 🚀**

Need help? Open an issue or check the Swagger docs!