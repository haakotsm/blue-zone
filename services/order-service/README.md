# Order Service

## Overview
The Order Service is the core service responsible for managing order lifecycle in the Blue Zone e-commerce platform. It handles order creation, status tracking, and orchestrates the order fulfillment process through integration with other microservices.

## Current Implementation Status

### ✅ What's Already Implemented
- **Basic CRUD Operations**: Create, read, update, delete orders
- **REST API Endpoints**: Full REST controller with validation
- **Data Model**: JPA entities for Order, OrderItem, and OrderStatus
- **Kafka Integration**: Publishes order events to Kafka topics
- **Repository Layer**: Spring Data JPA repositories
- **Basic Service Layer**: Order management service with transaction support

### 🔧 Current Endpoints
```
POST   /api/orders              - Create new order
GET    /api/orders/{id}         - Get order by ID
GET    /api/orders              - Get all orders
GET    /api/orders/customer/{customerId} - Get orders by customer
PUT    /api/orders/{id}/status  - Update order status
DELETE /api/orders/{id}         - Delete order
```

## 🎯 Workshop Assignments & Enhancement Requirements

### Assignment 1: Order Validation & Business Rules
**Difficulty: Beginner**

**Requirements:**
- Add comprehensive order validation:
  - Customer ID must not be empty
  - Order must have at least one item
  - Total amount must be positive and match sum of items
  - Each order item must have valid product ID and quantity > 0

**Implementation Tasks:**
- Create `OrderValidationService` class
- Add custom validation annotations
- Implement pre-save validation hooks
- Add proper error responses with validation messages

**Expected Files to Create:**
- `src/main/java/com/bluezone/order/service/OrderValidationService.java`
- `src/main/java/com/bluezone/order/validator/OrderValidator.java`
- `src/main/java/com/bluezone/order/exception/OrderValidationException.java`

### Assignment 2: Inventory Integration
**Difficulty: Intermediate**

**Requirements:**
- Integrate with Inventory Service before confirming orders
- Check product availability and reserve inventory
- Handle inventory reservation failures
- Implement compensation logic for failed orders

**Implementation Tasks:**
- Create `InventoryClient` for service-to-service communication
- Add inventory check before order confirmation
- Implement inventory reservation workflow
- Add rollback mechanism for failed inventory checks

**Expected Files to Create:**
- `src/main/java/com/bluezone/order/client/InventoryClient.java`
- `src/main/java/com/bluezone/order/service/InventoryIntegrationService.java`
- `src/main/java/com/bluezone/order/config/RestTemplateConfig.java`

### Assignment 3: Order Workflow State Machine
**Difficulty: Advanced**

**Requirements:**
- Implement proper order state transitions
- Add business rules for status changes
- Implement timeout handling for stuck orders
- Add order cancellation workflows

**Valid State Transitions:**
```
PENDING → CONFIRMED → PROCESSING → SHIPPED → DELIVERED
       ↘ CANCELLED ←──────────────────────────┘
```

**Implementation Tasks:**
- Create `OrderStateMachine` service
- Add state transition validation
- Implement scheduled tasks for timeout handling
- Add cancellation workflow with compensation

**Expected Files to Create:**
- `src/main/java/com/bluezone/order/service/OrderStateMachine.java`
- `src/main/java/com/bluezone/order/scheduler/OrderTimeoutHandler.java`
- `src/main/java/com/bluezone/order/service/OrderCancellationService.java`

### Assignment 4: Enhanced Kafka Integration
**Difficulty: Intermediate**

**Requirements:**
- Listen to payment events and update order status
- Listen to inventory events for stock updates
- Implement event sourcing for order history
- Add dead letter queue handling

**Implementation Tasks:**
- Create Kafka listeners for payment and inventory events
- Implement event sourcing pattern
- Add retry logic and error handling
- Create order event history tracking

**Expected Files to Create:**
- `src/main/java/com/bluezone/order/listener/PaymentEventListener.java`
- `src/main/java/com/bluezone/order/listener/InventoryEventListener.java`
- `src/main/java/com/bluezone/order/service/OrderEventSourcingService.java`
- `src/main/java/com/bluezone/order/model/OrderEvent.java`

### Assignment 5: Order Search & Filtering
**Difficulty: Beginner-Intermediate**

**Requirements:**
- Add advanced search capabilities
- Implement filtering by date range, status, customer
- Add pagination and sorting
- Implement order analytics endpoints

**Implementation Tasks:**
- Extend repository with custom queries
- Add search DTOs and criteria
- Implement pagination with Spring Data
- Create analytics aggregation queries

**Expected Files to Create:**
- `src/main/java/com/bluezone/order/dto/OrderSearchCriteria.java`
- `src/main/java/com/bluezone/order/service/OrderSearchService.java`
- `src/main/java/com/bluezone/order/controller/OrderAnalyticsController.java`

## 🧪 Testing Requirements

Each assignment should include:
- Unit tests for service classes
- Integration tests for REST endpoints
- Kafka integration tests
- Performance tests for search functionality

## 📋 Environment Setup

### Required Dependencies (already configured)
- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Kafka
- H2 Database (for development)
- Spring Boot Starter Validation

### Configuration Files
- `application.yml` - Database and Kafka configuration
- `docker-compose.yml` - Service dependencies

## 🔄 Integration Points

### Outgoing Dependencies
- **Inventory Service**: Product availability checks
- **Kafka**: Order event publishing

### Incoming Dependencies
- **Payment Service**: Payment status updates
- **Frontend**: Order management UI

## 🎯 Learning Objectives
- Microservices communication patterns
- Event-driven architecture
- State machine implementation
- Service integration and compensation patterns
- RESTful API design
- Data validation and error handling
