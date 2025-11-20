# E-Commerce Microservices Backend

A scalable, Spring Boot-based microservices architecture for an e-commerce platform, featuring service discovery, configuration management, and inter-service communication.

## 🏗️ Project Overview

This project implements a distributed e-commerce backend using a microservices architecture with Spring Cloud. The system is designed to handle customer management, inventory tracking, billing operations, and API gateway routing.

## 📊 System Architecture

```
┌─────────────────────────────────────────────────────────┐
│                   Client Applications                     │
└─────────────────────────────────────────────────────────┘
                          │
                          ▼
┌─────────────────────────────────────────────────────────┐
│              API Gateway Service (8080)                  │
└─────────────────────────────────────────────────────────┘
    │               │               │               │
    ▼               ▼               ▼               ▼
┌─────────────┐ ┌──────────────┐ ┌──────────┐ ┌────────┐
│ Customer    │ │ Inventory    │ │ Billing  │ │Config  │
│ Service     │ │ Service      │ │ Service  │ │Server  │
│ (8081)      │ │ (8082)       │ │ (8083)   │ │(8888)  │
└─────────────┘ └──────────────┘ └──────────┘ └────────┘
                      │
                      ▼
        ┌──────────────────────────────┐
        │  Eureka Discovery Service    │
        │         (8761)               │
        └──────────────────────────────┘
```


## 📦 Microservices

### 1. **Discovery Service** (Eureka)
- Service discovery server that manages service registration and discovery
- Port: 8761
- **Purpose**: Enables dynamic service discovery across the ecosystem

### 2. **Config Service**
- Centralized configuration management server
- Manages configuration properties for all microservices
- **Purpose**: Provides externalized configuration for all services

### 3. **Customer Service**
- Manages customer data and profiles
- REST API for CRUD operations on customers
- Database: H2 (In-Memory)
- **Features**: 
  - Customer information management
  - Service registration with Eureka

### 4. **Inventory Service**
- Manages product inventory and stock levels
- REST API for product operations
- Database: H2 (In-Memory)
- **Features**: 
  - Product management
  - Stock tracking
  - Service registration with Eureka

### 5. **Billing Service**
- Processes billing and invoicing operations
- Creates bills and tracks product items in orders
- Database: H2 (In-Memory)
- **Features**: 
  - Bill generation
  - Product item tracking per bill
  - Feign client integration with Customer and Inventory services
  - Automatic bill and product item creation at startup

### 6. **Gateway Service** (API Gateway)
- Routes client requests to appropriate microservices
- Single entry point for the entire backend
- Uses Spring Cloud Gateway for intelligent routing
- **Purpose**: Provides unified access point and load distribution

## 🛠️ Technology Stack

- **Framework**: Spring Boot 3.5.7
- **Cloud**: Spring Cloud 2025.0.0
- **Java Version**: 17-21
- **Build Tool**: Maven
- **Service Discovery**: Eureka
- **Configuration Management**: Spring Cloud Config
- **Inter-Service Communication**: Feign Client
- **Database**: H2 (In-Memory for development)
- **API Documentation**: Spring Data REST, HATEOAS

## 📋 Key Dependencies

- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Boot Starter Data REST
- Spring HATEOAS
- Spring Cloud Eureka Client/Server
- Spring Cloud Config Server/Client
- OpenFeign (for inter-service communication)
- Lombok (data class annotation)
- H2 Database

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- Git

### Installation & Running Services

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd ecom-app-microservices
   ```

2. **Start Discovery Service** (Eureka)
   ```bash
   cd discovery-service
   mvn spring-boot:run
   ```
   Access at: http://localhost:8761

3. **Start Config Service**
   ```bash
   cd config-service
   mvn spring-boot:run
   ```

4. **Start Microservices** (in any order)
   ```bash
   cd customer-service
   mvn spring-boot:run
   ```
   ```bash
   cd inventory-service
   mvn spring-boot:run
   ```
   ```bash
   cd billing-service
   mvn spring-boot:run
   ```

5. **Start Gateway Service**
   ```bash
   cd gateway-service
   mvn spring-boot:run
   ```

### Service Ports & Endpoints

| Service | Port | Endpoint |
|---------|------|----------|
| Discovery (Eureka) | 8761 | http://localhost:8761 |
| Config Service | 8888 | http://localhost:8888 |
| Customer Service | 8081 | http://localhost:8081 |
| Inventory Service | 8082 | http://localhost:8082 |
| Billing Service | 8083 | http://localhost:8083 |
| Gateway Service | 8080 | http://localhost:8080 |

## 🔄 Inter-Service Communication

- **Billing Service** uses **Feign Clients** to communicate with:
  - Customer Service (retrieve customer data)
  - Inventory Service (retrieve product information)

- All services register with **Eureka** for dynamic discovery
- Configuration is managed centrally via **Config Service**

## 📂 Project Structure

```
ecom-app-microservices/
├── billing-service/          # Billing & invoicing service
├── customer-service/         # Customer management service
├── inventory-service/        # Product inventory service
├── discovery-service/        # Eureka discovery server
├── config-service/           # Configuration management server
├── gateway-service/          # API Gateway
├── config-repo/              # Centralized configuration files
├── pom.xml                   # Parent POM
└── README.md                 # This file
```

## 🧪 Testing

Each service includes a test directory. Run tests with:
```bash
cd <service-name>
mvn test
```

## 🔐 Configuration Management

All services retrieve their configuration from the Config Server, which reads from the `config-repo/` directory:
- `application.properties` - Common configuration
- `billing-service.properties` - Billing service config
- `customer-service.properties` - Customer service config
- `inventory-service.properties` - Inventory service config

## 📝 Database

The project uses **H2 Database** for development/testing:
- In-memory database for rapid development
- H2 Console enabled for inspection
- Access H2 Console: http://localhost:<port>/h2-console

## 🤝 Key Features

✅ **Service Discovery** - Dynamic service registration and discovery via Eureka  
✅ **Centralized Configuration** - Externalized config management via Spring Cloud Config  
✅ **API Gateway** - Single entry point for all client requests  
✅ **Inter-Service Communication** - Feign clients for REST calls between services  
✅ **RESTful APIs** - Spring Data REST for automatic CRUD endpoints  
✅ **HATEOAS** - Hypermedia support for better API discoverability  
✅ **Health Monitoring** - Actuator endpoints for service health checks  
✅ **Scalability** - Loosely coupled, independently deployable services

## 📖 API Endpoints

Services expose RESTful endpoints through Spring Data REST. Common patterns:

- `GET /api/<resource>` - List all resources
- `GET /api/<resource>/{id}` - Get specific resource
- `POST /api/<resource>` - Create new resource
- `PUT /api/<resource>/{id}` - Update resource
- `DELETE /api/<resource>/{id}` - Delete resource

Access endpoints through the **Gateway Service** at `http://localhost:8080`

## 🚦 Health Checks

Each service exposes health information via Actuator:
```
http://localhost:<port>/actuator/health
```

All endpoints available at:
```
http://localhost:<port>/actuator
```

## 💾 Data Initialization

The **Billing Service** includes a `CommandLineRunner` that automatically:
1. Retrieves all customers via Feign Client
2. Retrieves all products via Feign Client
3. Creates bills for each customer
4. Associates random quantities of products with each bill

This initialization runs on application startup.

## 🔧 Development

### Useful Maven Commands

```bash
# Build a service
mvn clean install

# Run with Spring Boot Maven plugin
mvn spring-boot:run

# Run tests
mvn test

# Package as JAR
mvn package

# Skip tests during build
mvn clean install -DskipTests
```

### IDE Setup

1. Open the project in your favorite IDE (IntelliJ IDEA, VS Code, Eclipse)
2. Ensure Java 17+ is configured
3. Maven should automatically download dependencies

## 📚 Documentation

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Cloud Documentation](https://spring.io/projects/spring-cloud)
- [Eureka Discovery Documentation](https://github.com/Netflix/eureka)
- [Spring Cloud Config Documentation](https://spring.io/projects/spring-cloud-config)

