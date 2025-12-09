# Start all microservices
Write-Host "Starting E-Commerce Microservices..." -ForegroundColor Green

# Start User Service (Port 8080)
Write-Host "`nStarting User Service on port 8080..." -ForegroundColor Cyan
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$PSScriptRoot'; ./mvnw spring-boot:run -pl user-service"

# Wait a bit for User Service to start
Start-Sleep -Seconds 5

# Start Product Service (Port 8081)
Write-Host "Starting Product Service on port 8081..." -ForegroundColor Cyan
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$PSScriptRoot'; ./mvnw spring-boot:run -pl product-service"

# Wait a bit
Start-Sleep -Seconds 5

# Start Order Service (Port 8082)
Write-Host "Starting Order Service on port 8082..." -ForegroundColor Cyan
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$PSScriptRoot'; ./mvnw spring-boot:run -pl order-service"

Write-Host "`n✅ All services are starting..." -ForegroundColor Green
Write-Host "`nServices will be available at:" -ForegroundColor Yellow
Write-Host "  - User Service:    http://localhost:8080/swagger-ui.html" -ForegroundColor White
Write-Host "  - Product Service: http://localhost:8081/swagger-ui.html" -ForegroundColor White
Write-Host "  - Order Service:   http://localhost:8082/swagger-ui.html" -ForegroundColor White
