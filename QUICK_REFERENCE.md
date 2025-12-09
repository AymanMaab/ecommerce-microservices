# 🎯 Quick Reference - E-Commerce Microservices

## 🚀 QUICK START COMMANDS

```powershell
# Start MongoDB (Docker)
docker run -d -p 27017:27017 --name mongodb mongo:7.0

# Start all services at once
.\start-services.ps1

# OR start individually
.\mvnw spring-boot:run -pl user-service     # Port 8080
.\mvnw spring-boot:run -pl product-service  # Port 8081
.\mvnw spring-boot:run -pl order-service    # Port 8082
```

## 🌐 SERVICE URLS

| Service | Swagger UI | Base API |
|---------|-----------|----------|
| **User** | http://localhost:8080/swagger-ui.html | http://localhost:8080/api/users |
| **Product** | http://localhost:8081/swagger-ui.html | http://localhost:8081/api/products |
| **Order** | http://localhost:8082/swagger-ui.html | http://localhost:8082/api/orders |

## 📝 DEMO DATA (Copy & Paste)

### Create User
```json
{
  "firstName": "Jane",
  "lastName": "Smith",
  "email": "jane.smith@example.com",
  "phone": "9876543210",
  "address": "456 Oak Avenue, San Francisco, CA"
}
```

### Create Product (Laptop)
```json
{
  "sku": "LAP-001",
  "name": "Dell XPS 15 Developer Edition",
  "description": "High-performance laptop with 32GB RAM, Intel i9",
  "category": "ELECTRONICS",
  "price": 2499.99,
  "stockQuantity": 25
}
```

### Create Product (Book)
```json
{
  "sku": "BOOK-001",
  "name": "Clean Code by Robert Martin",
  "description": "A handbook of agile software craftsmanship",
  "category": "BOOKS",
  "price": 39.99,
  "stockQuantity": 100
}
```

### Create Product (Phone)
```json
{
  "sku": "PHN-001",
  "name": "iPhone 15 Pro Max",
  "description": "Latest flagship smartphone with A17 Pro chip",
  "category": "ELECTRONICS",
  "price": 1199.99,
  "stockQuantity": 50
}
```

## 🎬 DEMO FLOW

### 1. User Management
```
1. POST /api/users → Create user
2. GET /api/users → Show all users
3. GET /api/users/email/{email} → Find by email
4. Try duplicate email → Show error handling
```

### 2. Product Catalog
```
1. POST /api/products → Create 3 products (laptop, book, phone)
2. GET /api/products → Show all
3. GET /api/products/category/ELECTRONICS → Filter
4. GET /api/products/search?query=clean → Search
5. PATCH /api/products/{id}/stock?quantity=200 → Update stock
```

### 3. Order Processing
```
1. POST /api/orders → Create order
2. GET /api/orders/user/{userId} → User's orders
3. GET /api/orders/analytics/revenue → Show aggregation
```

## 🏗️ ARCHITECTURE LAYERS

```
Controller → Service → Repository → MongoDB
   ↓           ↓          ↓
 HTTP      Business    Database
Request     Logic       Access
```

## 🔑 KEY TALKING POINTS

### For Supervisor:
- ✅ "Independent deployment of services"
- ✅ "Follows industry best practices"
- ✅ "Comprehensive API documentation"
- ✅ "Production-ready error handling"

### For CTO:
- ✅ "Scalable microservices architecture"
- ✅ "Java 21 LTS for long-term stability"
- ✅ "MongoDB for flexible schema evolution"
- ✅ "Ready for containerization (Docker/K8s)"

## 🐛 TROUBLESHOOTING

### Service won't start
```powershell
# Check MongoDB is running
docker ps | Select-String mongodb

# Check port not in use
netstat -ano | findstr :8080

# Clean rebuild
.\mvnw clean install
```

### Can't connect to MongoDB
```powershell
# Test MongoDB connection
mongosh mongodb://localhost:27017

# Or use MongoDB Compass GUI
```

## 📊 TECH STACK SUMMARY

| Layer | Technology | Version |
|-------|-----------|---------|
| Language | Java | 21 (LTS) |
| Framework | Spring Boot | 3.5.0 |
| Database | MongoDB | 7.0+ |
| Build | Maven | 3.9+ |
| Docs | Swagger/OpenAPI | 3.0 |

## 🎯 ONE-LINER EXPLANATIONS

**What is this project?**
> "A modern microservices e-commerce backend with 3 independent services (User, Product, Order) built on Java 21 and Spring Boot, using MongoDB for data storage and Swagger for API documentation."

**Why microservices?**
> "Independent deployment, better scalability, team autonomy, and fault isolation - if one service fails, others keep running."

**Why MongoDB?**
> "Flexible schema for evolving product catalogs, JSON-native for REST APIs, and horizontal scaling for growth."

**Why Java 21?**
> "Latest LTS version with virtual threads for better performance, long-term support until 2029, and industry-standard for enterprise applications."

## 📞 EMERGENCY COMMANDS

```powershell
# Kill all Java processes
Get-Process java | Stop-Process -Force

# Stop MongoDB Docker
docker stop mongodb

# Rebuild everything
.\mvnw clean install -DskipTests

# Check what's running
Get-Process | Where-Object {$_.ProcessName -match "java"}
```

---

**Pro Tip:** Keep this file open during your presentation for quick reference! 🚀
