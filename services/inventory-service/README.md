# Inventory Service

## Overview
The Inventory Service manages product catalog, stock levels, and inventory operations in the Blue Zone e-commerce platform. This service is currently a **minimal shell** and requires complete implementation as part of the workshop assignments.

## Current Implementation Status

### ✅ What's Already Implemented
- **Basic Spring Boot Application**: Application shell with Kafka enabled
- **Project Structure**: Maven project with Spring Boot dependencies
- **Kafka Integration**: @EnableKafka annotation (but no listeners implemented)

### ❌ What's Missing (Everything!)
- **Data Models**: No product or inventory entities
- **REST APIs**: No controllers implemented
- **Business Logic**: No inventory management services
- **Database Integration**: No repositories or data access
- **Kafka Listeners**: No event handling implemented

## 🎯 Workshop Assignments & Implementation Requirements

### Assignment 1: Core Data Models & Repository Layer
**Difficulty: Beginner**

**Requirements:**
- Create Product entity with basic product information
- Create Inventory entity for stock management
- Create Stock Movement entity for tracking changes
- Implement JPA repositories for all entities

**Data Model Requirements:**

**Product Entity:**
```java
- Long id (Primary Key)
- String productCode (Unique)
- String name
- String description
- String category
- BigDecimal price
- String sku
- Boolean active
- LocalDateTime createdAt
- LocalDateTime updatedAt
```

**Inventory Entity:**
```java
- Long id (Primary Key)
- Long productId (Foreign Key)
- Integer availableQuantity
- Integer reservedQuantity
- Integer minimumThreshold
- String warehouseLocation
- LocalDateTime lastUpdated
```

**Stock Movement Entity:**
```java
- Long id (Primary Key)
- Long productId
- String movementType (IN, OUT, RESERVED, RELEASED)
- Integer quantity
- String reason
- String orderId (optional)
- LocalDateTime timestamp
```

**Expected Files to Create:**
- `src/main/java/com/bluezone/inventory/model/Product.java`
- `src/main/java/com/bluezone/inventory/model/Inventory.java`
- `src/main/java/com/bluezone/inventory/model/StockMovement.java`
- `src/main/java/com/bluezone/inventory/model/MovementType.java` (enum)
- `src/main/java/com/bluezone/inventory/repository/ProductRepository.java`
- `src/main/java/com/bluezone/inventory/repository/InventoryRepository.java`
- `src/main/java/com/bluezone/inventory/repository/StockMovementRepository.java`

### Assignment 2: Product Management Service & REST API
**Difficulty: Beginner-Intermediate**

**Requirements:**
- Implement complete product management functionality
- Create REST API for product CRUD operations
- Add product search and filtering capabilities
- Implement product categorization

**Implementation Tasks:**
- Create `ProductService` with business logic
- Implement `ProductController` with full REST API
- Add validation for product data
- Implement search functionality with pagination

**API Endpoints to Implement:**
```
POST   /api/products                    - Create product
GET    /api/products/{id}               - Get product by ID
GET    /api/products                    - Get all products (with pagination)
GET    /api/products/search             - Search products by criteria
GET    /api/products/category/{category} - Get products by category
PUT    /api/products/{id}               - Update product
DELETE /api/products/{id}               - Delete product
GET    /api/products/{id}/inventory     - Get product inventory status
```

**Expected Files to Create:**
- `src/main/java/com/bluezone/inventory/service/ProductService.java`
- `src/main/java/com/bluezone/inventory/controller/ProductController.java`
- `src/main/java/com/bluezone/inventory/dto/ProductDTO.java`
- `src/main/java/com/bluezone/inventory/dto/ProductSearchCriteria.java`
- `src/main/java/com/bluezone/inventory/validator/ProductValidator.java`

### Assignment 3: Inventory Management & Stock Operations
**Difficulty: Intermediate**

**Requirements:**
- Implement inventory tracking and management
- Create stock adjustment operations (add, remove, transfer)
- Implement low stock monitoring and alerts
- Add inventory reservation system for order processing

**Implementation Tasks:**
- Create `InventoryService` with stock management logic
- Implement stock adjustment operations
- Add automatic low stock detection
- Create inventory reservation/release mechanisms
- Implement stock movement tracking

**API Endpoints to Implement:**
```
GET    /api/inventory/{productId}           - Get inventory for product
POST   /api/inventory/{productId}/adjust    - Adjust stock levels
POST   /api/inventory/{productId}/reserve   - Reserve inventory for order
POST   /api/inventory/{productId}/release   - Release reserved inventory
GET    /api/inventory/low-stock             - Get products with low stock
GET    /api/inventory/movements/{productId} - Get stock movement history
```

**Expected Files to Create:**
- `src/main/java/com/bluezone/inventory/service/InventoryService.java`
- `src/main/java/com/bluezone/inventory/controller/InventoryController.java`
- `src/main/java/com/bluezone/inventory/dto/StockAdjustmentDTO.java`
- `src/main/java/com/bluezone/inventory/dto/ReservationRequest.java`
- `src/main/java/com/bluezone/inventory/service/StockMovementService.java`

### Assignment 4: Kafka Integration & Event-Driven Inventory
**Difficulty: Intermediate**

**Requirements:**
- Listen to order events for automatic inventory reservation
- Publish inventory events for other services
- Implement inventory availability checks for orders
- Handle order cancellation to release reserved inventory

**Implementation Tasks:**
- Create Kafka listeners for order events
- Implement automatic inventory reservation on order creation
- Create Kafka producers for inventory events
- Add event-driven stock updates
- Implement compensation logic for failed orders

**Kafka Topics & Events:**
```
Consumed Topics:
- order-events: order.created, order.cancelled, order.status.updated

Published Topics:
- inventory-events: stock.reserved, stock.released, stock.low, stock.updated
```

**Expected Files to Create:**
- `src/main/java/com/bluezone/inventory/listener/OrderEventListener.java`
- `src/main/java/com/bluezone/inventory/service/InventoryEventService.java`
- `src/main/java/com/bluezone/inventory/dto/InventoryEvent.java`
- `src/main/java/com/bluezone/inventory/config/KafkaConfig.java`

### Assignment 5: Advanced Inventory Features
**Difficulty: Advanced**

**Requirements:**
- Implement multi-warehouse inventory management
- Add inventory forecasting and reorder point calculation
- Implement batch operations for bulk inventory updates
- Add inventory valuation and cost tracking

**Implementation Tasks:**
- Create multi-warehouse support
- Implement automatic reorder point calculation
- Add batch processing for inventory updates
- Create inventory valuation algorithms
- Implement ABC analysis for inventory classification

**Expected Files to Create:**
- `src/main/java/com/bluezone/inventory/model/Warehouse.java`
- `src/main/java/com/bluezone/inventory/service/InventoryForecastingService.java`
- `src/main/java/com/bluezone/inventory/service/BatchInventoryService.java`
- `src/main/java/com/bluezone/inventory/service/InventoryValuationService.java`
- `src/main/java/com/bluezone/inventory/analytics/InventoryAnalyticsService.java`

### Assignment 6: Inventory Reporting & Analytics
**Difficulty: Intermediate**

**Requirements:**
- Create inventory reporting dashboard data
- Implement stock turnover analysis
- Add inventory aging reports
- Create real-time inventory metrics

**Implementation Tasks:**
- Create inventory analytics aggregations
- Implement turnover ratio calculations
- Add aging analysis for slow-moving stock
- Create real-time inventory dashboards
- Implement inventory performance metrics

**Expected Files to Create:**
- `src/main/java/com/bluezone/inventory/reporting/InventoryReportService.java`
- `src/main/java/com/bluezone/inventory/controller/InventoryAnalyticsController.java`
- `src/main/java/com/bluezone/inventory/dto/InventoryMetrics.java`
- `src/main/java/com/bluezone/inventory/dto/TurnoverAnalysis.java`

## 🧪 Testing Requirements

Each assignment should include:
- Unit tests for service classes
- Integration tests for REST endpoints
- Kafka integration tests for event handling
- Repository tests for data access
- Performance tests for bulk operations

## 📋 Environment Setup

### Required Dependencies (already configured)
- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Kafka
- H2 Database (for development)
- Spring Boot Starter Validation

### Additional Dependencies Needed
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

### Database Configuration
Add to `application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:h2:mem:inventory
    driver-class-name: org.h2.Driver
    username: sa
    password: password
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: create-drop
    show-sql: true
  h2:
    console:
      enabled: true
```

## 🔄 Integration Points

### Incoming Dependencies
- **Order Service**: Order events for inventory reservation
- **Frontend**: Product catalog and inventory management

### Outgoing Dependencies
- **Order Service**: Inventory availability confirmations
- **Notification Service**: Low stock alerts
- **Kafka**: Inventory event publishing

## 📊 Business Rules to Implement

### Inventory Reservation Rules
- Products must have sufficient available quantity
- Reserved quantity cannot exceed available quantity
- Reservations should have timeout (auto-release after X minutes)
- Multiple reservations for same product should be handled atomically

### Stock Management Rules
- Available quantity = Total quantity - Reserved quantity
- Low stock threshold should trigger automatic alerts
- Stock movements must be tracked for audit purposes
- Negative stock should be prevented or flagged

### Product Management Rules
- Product codes must be unique
- Active products should be available for ordering
- Product price changes should be tracked
- Products with existing inventory cannot be deleted

## 🎯 Learning Objectives
- Complete microservice implementation from scratch
- Inventory management domain knowledge
- Event-driven architecture patterns
- Concurrent inventory operations and locking
- Business rule implementation
- Data consistency in distributed systems
- REST API design for domain-specific operations
