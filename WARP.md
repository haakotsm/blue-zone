# WARP.md

This file provides guidance to WARP (warp.dev) when working with code in this repository.

## Project Overview

This is a microservice ordering system for "blå sone" (blue zone) built with Spring Boot, Kafka, and a React monitoring frontend. The system demonstrates event-driven architecture with multiple services communicating through Kafka.

## Project Structure

This is a monorepo containing multiple standalone microservices:

```
blue-zone/
├── docker-compose.yml         # Complete system orchestration
├── services/                  # Spring Boot microservices
│   ├── order-service/        # Order management (Port 8081)
│   ├── payment-service/      # Payment processing (Port 8082)
│   ├── inventory-service/    # Inventory management (Port 8083)
│   └── notification-service/ # Notifications (Port 8084)
└── frontend-client/          # Next.js monitoring dashboard (Port 3000)
```

### Technology Stack

- **Java 21** with **Spring Boot 3.4.1**
- **Node.js 20** with **React 19** and **Next.js 15**
- **Kafka** for event streaming
- **H2** in-memory databases for development
- **Docker** for containerization

### Architecture Components

- **Order Service**: Manages order lifecycle, publishes order events
- **Payment Service**: Processes payments, listens to order events
- **Inventory Service**: Manages stock, validates product availability
- **Notification Service**: Sends email/SMS notifications
- **Frontend Client**: Real-time monitoring dashboard
- **Kafka**: Event streaming platform for service communication
- **Zookeeper**: Kafka coordination service

## Development Commands

### Building the Entire System
```bash
# Build all Spring Boot services
for service in services/*/; do (cd "$service" && mvn clean package); done

# Or build services individually
cd services/order-service && mvn clean package
cd services/payment-service && mvn clean package
cd services/inventory-service && mvn clean package
cd services/notification-service && mvn clean package

# Build frontend client
cd frontend-client && npm install && npm run build
```

### Running with Docker Compose
```bash
# Start the entire system
docker-compose up -d

# View logs for all services
docker-compose logs -f

# View logs for specific service
docker-compose logs -f order-service

# Stop the system
docker-compose down

# Rebuild and restart
docker-compose up --build -d
```

### Running Individual Services (Development)
```bash
# Start Kafka and Zookeeper first
docker-compose up -d zookeeper kafka

# Run services individually
cd services/order-service && mvn spring-boot:run
cd services/payment-service && mvn spring-boot:run
cd services/inventory-service && mvn spring-boot:run
cd services/notification-service && mvn spring-boot:run

# Run frontend client
cd frontend-client && npm run dev
```

### Testing
```bash
# Run all service tests
for service in services/*/; do (cd "$service" && mvn test); done

# Run tests for specific service
cd services/order-service && mvn test
cd services/payment-service && mvn test
cd services/inventory-service && mvn test
cd services/notification-service && mvn test

# Run integration tests with Testcontainers
cd services/order-service && mvn verify

# Test frontend
cd frontend-client && npm test
```

### Service Management
```bash
# Check service health
curl http://localhost:8081/actuator/health  # Order Service
curl http://localhost:8082/actuator/health  # Payment Service
curl http://localhost:8083/actuator/health  # Inventory Service
curl http://localhost:8084/actuator/health  # Notification Service

# View service metrics
curl http://localhost:8081/actuator/metrics

# H2 Database consoles (development)
open http://localhost:8081/h2-console  # Order DB
open http://localhost:8082/h2-console  # Payment DB
open http://localhost:8083/h2-console  # Inventory DB
```

### Kafka Operations
```bash
# Access Kafka UI
open http://localhost:8090

# View MailHog (email testing)
open http://localhost:8025

# Create Kafka topics manually (if needed)
docker-compose exec kafka kafka-topics --create --topic order-events --bootstrap-server localhost:9092
docker-compose exec kafka kafka-topics --create --topic payment-events --bootstrap-server localhost:9092
```

## API Endpoints

### Order Service (8081)
- `GET /api/orders` - List all orders
- `POST /api/orders` - Create new order
- `GET /api/orders/{id}` - Get order by ID
- `PUT /api/orders/{id}/status` - Update order status

### Frontend Monitoring (3000)
- `http://localhost:3000` - Real-time dashboard

## Event Flow

1. **Order Created** → Order Service publishes `order.created` event
2. **Payment Processing** → Payment Service consumes event, processes payment
3. **Inventory Check** → Inventory Service validates stock availability
4. **Notifications** → Notification Service sends status updates
5. **Frontend Updates** → Dashboard polls for real-time status

## Database Configuration

- All services use H2 in-memory databases for development
- Database consoles available at `/h2-console` on each service
- Default credentials: `sa` / `password`

## Development Tips

- Services start on ports 8081-8084 sequentially
- Kafka runs on port 9092
- Frontend development server on port 3000
- All services include health checks and metrics endpoints
- Use Docker Compose for full system testing
- Individual service development can use local Kafka instance

## Troubleshooting

- If Kafka fails to start, ensure no other Kafka instances are running
- Check Docker container logs: `docker-compose logs [service-name]`
- Verify port availability: `lsof -i :8081`
- Services have startup dependencies - wait for Kafka to be healthy
- Frontend CORS issues: Services configured to allow localhost origins
