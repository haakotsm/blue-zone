# Frontend Client

## Overview
The Frontend Client is a Next.js React application that provides the user interface for the Blue Zone e-commerce platform. It serves as the customer-facing dashboard for viewing orders, managing services, and interacting with the microservices ecosystem.

## Current Implementation Status

### ✅ What's Already Implemented
- **Next.js Application**: Basic Next.js 14 setup with TypeScript
- **UI Components**: 
  - `ServiceStatus` component for displaying service health
  - `OrderMonitor` component for displaying orders
- **Basic Styling**: Tailwind CSS setup for responsive design
- **TypeScript Configuration**: Proper type definitions for components
- **Docker Support**: Containerized with multi-stage build

### 🔧 Current Components

**ServiceStatus Component:**
- Displays status of all microservices
- Shows service health indicators (✅ healthy, ⚠️ unhealthy, ❌ down)
- Static status display (currently shows "unknown" for all services)

**OrderMonitor Component:**
- Table view for displaying orders
- Order status with color-coded badges
- Static data structure (no API integration)

### ❌ What's Missing
- **API Integration**: No actual calls to backend services
- **Authentication**: No user login or authentication system
- **Order Management**: No order creation or management functionality
- **Real-time Updates**: No live status updates or WebSocket integration
- **Navigation**: No routing or multi-page navigation
- **Error Handling**: No proper error states or loading indicators

## 🎯 Workshop Assignments & Enhancement Requirements

### Assignment 1: API Integration & Service Communication
**Difficulty: Beginner-Intermediate**

**Requirements:**
- Integrate with all backend service APIs
- Implement proper HTTP client with error handling
- Add loading states and error handling
- Create proper data fetching patterns

**Implementation Tasks:**
- Set up Axios or Fetch API client
- Create API service layer for each microservice
- Implement proper error handling and retry logic
- Add loading indicators and error states
- Create type-safe API responses

**API Endpoints to Integrate:**
```
Order Service (Port 8081):
GET    /api/orders                    - Fetch all orders
POST   /api/orders                    - Create new order
GET    /api/orders/{id}               - Get specific order
PUT    /api/orders/{id}/status        - Update order status

Payment Service (Port 8082):
GET    /api/payments                  - Fetch payment history
POST   /api/payments/process          - Process payment

Inventory Service (Port 8083):
GET    /api/products                  - Fetch product catalog
GET    /api/inventory/{productId}     - Check product stock

Notification Service (Port 8084):
GET    /api/notifications/{customerId} - Get notifications
PUT    /api/notifications/preferences  - Update preferences
```

**Expected Files to Create:**
- `src/services/api.ts` - Base API client configuration
- `src/services/orderService.ts` - Order API integration
- `src/services/paymentService.ts` - Payment API integration
- `src/services/inventoryService.ts` - Inventory API integration
- `src/services/notificationService.ts` - Notification API integration
- `src/types/api.ts` - API response type definitions
- `src/hooks/useApi.ts` - Custom hook for API calls

### Assignment 2: Real-time Service Status Monitoring
**Difficulty: Intermediate**

**Requirements:**
- Implement real service health checking
- Add automatic status updates with polling
- Create service availability indicators
- Add service performance metrics display

**Implementation Tasks:**
- Create health check endpoints integration
- Implement polling mechanism for status updates
- Add WebSocket connection for real-time updates
- Create service metrics dashboard
- Add historical status tracking

**Expected Files to Create:**
- `src/hooks/useServiceStatus.ts` - Service status monitoring hook
- `src/components/ServiceHealth.tsx` - Enhanced service health component
- `src/components/ServiceMetrics.tsx` - Service performance metrics
- `src/services/healthService.ts` - Health check API integration
- `src/utils/websocket.ts` - WebSocket connection management

### Assignment 3: Complete Order Management Interface
**Difficulty: Intermediate**

**Requirements:**
- Create order creation workflow
- Implement order status tracking
- Add order history and filtering
- Create order details view with real-time updates

**Implementation Tasks:**
- Design and implement order creation form
- Create order status timeline component
- Add order search and filtering functionality
- Implement order details modal/page
- Add order cancellation functionality

**Expected Files to Create:**
- `src/components/OrderCreation.tsx` - Order creation form
- `src/components/OrderDetails.tsx` - Detailed order view
- `src/components/OrderTimeline.tsx` - Order status progression
- `src/components/OrderFilters.tsx` - Order filtering controls
- `src/hooks/useOrders.ts` - Order management hook
- `src/pages/orders/[id].tsx` - Individual order page

### Assignment 4: Product Catalog & Inventory Management
**Difficulty: Intermediate**

**Requirements:**
- Create product catalog interface
- Implement inventory level displays
- Add product search and categorization
- Create low stock alerts and notifications

**Implementation Tasks:**
- Design product catalog grid/list view
- Implement product search with filters
- Create inventory status indicators
- Add product detail views
- Implement category navigation

**Expected Files to Create:**
- `src/components/ProductCatalog.tsx` - Product listing component
- `src/components/ProductCard.tsx` - Individual product card
- `src/components/ProductDetails.tsx` - Product detail view
- `src/components/InventoryStatus.tsx` - Stock level indicators
- `src/components/ProductSearch.tsx` - Search and filter controls
- `src/pages/products/index.tsx` - Product catalog page
- `src/pages/products/[id].tsx` - Product detail page

### Assignment 5: Payment Interface & History
**Difficulty: Advanced**

**Requirements:**
- Implement payment processing interface
- Create payment method management
- Add payment history and analytics
- Integrate with payment gateways (Stripe/PayPal)

**Implementation Tasks:**
- Create payment form with validation
- Implement payment method storage
- Add payment status tracking
- Create payment history dashboard
- Integrate with Stripe Elements or PayPal SDK

**Expected Files to Create:**
- `src/components/PaymentForm.tsx` - Payment processing form
- `src/components/PaymentMethods.tsx` - Stored payment methods
- `src/components/PaymentHistory.tsx` - Payment transaction history
- `src/components/PaymentStatus.tsx` - Payment status tracking
- `src/services/stripeService.ts` - Stripe integration
- `src/hooks/usePayments.ts` - Payment management hook

### Assignment 6: Notification Center & Preferences
**Difficulty: Beginner-Intermediate**

**Requirements:**
- Create notification center interface
- Implement notification preferences management
- Add real-time notification updates
- Create notification history and filtering

**Implementation Tasks:**
- Design notification center UI
- Implement notification preferences form
- Add real-time notification updates
- Create notification filtering and search
- Add notification actions (mark as read, delete)

**Expected Files to Create:**
- `src/components/NotificationCenter.tsx` - Notification display
- `src/components/NotificationPreferences.tsx` - Preference settings
- `src/components/NotificationItem.tsx` - Individual notification
- `src/hooks/useNotifications.ts` - Notification management
- `src/components/NotificationBell.tsx` - Notification indicator

### Assignment 7: Dashboard & Analytics
**Difficulty: Advanced**

**Requirements:**
- Create comprehensive dashboard with analytics
- Implement charts and data visualization
- Add performance metrics and KPIs
- Create admin interface for system monitoring

**Implementation Tasks:**
- Design dashboard layout with widgets
- Implement charts using Chart.js or D3
- Create KPI cards and metrics
- Add data export functionality
- Implement admin-specific views

**Expected Files to Create:**
- `src/components/Dashboard.tsx` - Main dashboard component
- `src/components/charts/OrderChart.tsx` - Order analytics chart
- `src/components/charts/PaymentChart.tsx` - Payment analytics
- `src/components/KPICard.tsx` - Key performance indicator cards
- `src/components/AdminDashboard.tsx` - Admin-specific dashboard
- `src/pages/dashboard.tsx` - Dashboard page

### Assignment 8: Authentication & User Management
**Difficulty: Advanced**

**Requirements:**
- Implement user authentication system
- Add role-based access control
- Create user profile management
- Integrate with backend authentication

**Implementation Tasks:**
- Set up authentication context
- Create login/register forms
- Implement JWT token management
- Add role-based route protection
- Create user profile pages

**Expected Files to Create:**
- `src/contexts/AuthContext.tsx` - Authentication context
- `src/components/LoginForm.tsx` - User login form
- `src/components/RegisterForm.tsx` - User registration
- `src/components/UserProfile.tsx` - User profile management
- `src/middleware/auth.ts` - Authentication middleware
- `src/hooks/useAuth.ts` - Authentication hook

## 🧪 Testing Requirements

Each assignment should include:
- Component unit tests with React Testing Library
- Integration tests for API interactions
- E2E tests with Playwright or Cypress
- Accessibility tests
- Performance tests for large data sets

## 📋 Environment Setup

### Current Dependencies
- Next.js 14
- React 18
- TypeScript
- Tailwind CSS
- ESLint

### Additional Dependencies Needed
```json
{
  "dependencies": {
    "axios": "^1.6.0",
    "react-query": "^3.39.0",
    "react-hook-form": "^7.47.0",
    "@stripe/stripe-js": "^2.1.0",
    "@stripe/react-stripe-js": "^2.3.0",
    "chart.js": "^4.4.0",
    "react-chartjs-2": "^5.2.0",
    "socket.io-client": "^4.7.0",
    "date-fns": "^2.30.0",
    "react-router-dom": "^6.17.0"
  },
  "devDependencies": {
    "@testing-library/react": "^13.4.0",
    "@testing-library/jest-dom": "^6.1.0",
    "@playwright/test": "^1.39.0",
    "cypress": "^13.5.0"
  }
}
```

### Environment Variables
```env
NEXT_PUBLIC_API_BASE_URL=http://localhost:8080
NEXT_PUBLIC_ORDER_SERVICE_URL=http://localhost:8081
NEXT_PUBLIC_PAYMENT_SERVICE_URL=http://localhost:8082
NEXT_PUBLIC_INVENTORY_SERVICE_URL=http://localhost:8083
NEXT_PUBLIC_NOTIFICATION_SERVICE_URL=http://localhost:8084
NEXT_PUBLIC_STRIPE_PUBLISHABLE_KEY=pk_test_...
NEXT_PUBLIC_WEBSOCKET_URL=ws://localhost:9090
```

## 🔄 Integration Points

### Backend Service Integration
- **Order Service**: Order creation, tracking, and management
- **Payment Service**: Payment processing and history
- **Inventory Service**: Product catalog and stock information
- **Notification Service**: Notifications and preferences

### External Services
- **Stripe**: Payment processing
- **WebSocket Server**: Real-time updates
- **Analytics Services**: User behavior tracking

## 📱 Responsive Design Requirements

### Mobile-First Approach
- Responsive design for mobile, tablet, and desktop
- Touch-friendly interfaces
- Optimized loading for mobile networks
- Progressive Web App (PWA) features

### Browser Compatibility
- Support for modern browsers (Chrome, Firefox, Safari, Edge)
- Graceful degradation for older browsers
- Cross-platform consistency

## 🎯 Learning Objectives
- Modern React development with hooks and context
- TypeScript for type-safe frontend development
- API integration and state management
- Real-time web application features
- Payment integration and security
- Responsive and accessible UI design
- Testing strategies for React applications
- Performance optimization techniques
- Progressive Web App development
