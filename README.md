# E-Commerce System Design

## Overview
This is a comprehensive e-commerce system implementation in Java that demonstrates low-level design best practices and SOLID principles.

## Design Principles Applied

### 1. Single Responsibility Principle (SRP)
- Each class has a single, well-defined responsibility
- `Product` handles product properties
- `Cart` manages cart operations
- `CheckoutService` handles checkout logic
- `ShippingService` manages shipping operations

### 2. Open/Closed Principle (OCP)
- Product hierarchy is open for extension (new product types) but closed for modification
- New product types can be added without changing existing code

### 3. Interface Segregation Principle (ISP)
- `Expirable` interface only for products that can expire
- `Shippable` interface only for products that require shipping
- Clients depend only on interfaces they use

### 4. Dependency Inversion Principle (DIP)
- `CheckoutService` depends on `ShippingService` abstraction
- High-level modules don't depend on low-level modules

### 5. Liskov Substitution Principle (LSP)
- All product subtypes can be substituted for the base `Product` class
- Behavioral contracts are maintained across inheritance hierarchy

## Architecture

### Model Layer
- **Product (Abstract)**: Base class for all products
- **PerishableProduct**: Products that expire and require shipping (Cheese, Biscuits)
- **ElectronicProduct**: Products that require shipping but don't expire (TV, Mobile)
- **DigitalProduct**: Products that don't expire and don't require shipping (Scratch cards)
- **Cart**: Shopping cart management
- **CartItem**: Individual cart item representation
- **Customer**: Customer entity with balance management

### Service Layer
- **CheckoutService**: Handles the complete checkout process
- **ShippingService**: Manages shipping logistics and cost calculation

### Exception Layer
- **CheckoutException**: Base checkout exception
- **InsufficientBalanceException**: Insufficient customer balance
- **OutOfStockException**: Product out of stock
- **ProductExpiredException**: Product has expired

## Features Implemented

1. ✅ Product management with name, price, and quantity
2. ✅ Expirable products (Cheese, Biscuits) with expiry date validation
3. ✅ Shippable products with weight management
4. ✅ Digital products that don't require shipping
5. ✅ Cart operations with quantity validation
6. ✅ Comprehensive checkout process with:
   - Order subtotal calculation
   - Shipping fees calculation
   - Total amount computation
   - Customer balance validation and deduction
   - Inventory management
7. ✅ Error handling for:
   - Empty cart
   - Insufficient balance
   - Out of stock products
   - Expired products
8. ✅ Shipping service integration with weight-based cost calculation
9. ✅ Receipt generation with detailed breakdown

## Key Design Decisions

### Product Hierarchy
- Used abstract base class `Product` with concrete implementations
- Applied composition over inheritance for behaviors (Expirable, Shippable)
- Each product type implements only relevant interfaces

### Error Handling
- Custom exception hierarchy for different error scenarios
- Comprehensive validation before processing checkout
- Graceful error messages for better user experience

### Service Layer
- Separated business logic into dedicated service classes
- Dependency injection for better testability
- Clear separation of concerns

## Running the Application

### Using Makefile (Recommended)
```bash
# Compile and run (default target)
make

# Or explicitly run
make run

# Clean compiled files
make clean

# Force rebuild (clean + compile + run)
make rebuild

# Show all available commands
make help
```

### Manual Compilation (Alternative)
```bash
# Compile
javac -d bin src/main/java/com/ecommerce/**/*.java

# Run
java -cp bin com.ecommerce.Main
```

## Test Cases Covered

1. **Successful Checkout**: Mixed products with shipping and digital items
2. **Empty Cart Error**: Attempting checkout with empty cart
3. **Insufficient Balance**: Customer doesn't have enough money
4. **Out of Stock**: Requesting more quantity than available
5. **Expired Product**: Attempting to buy expired items
6. **Digital Only**: Checkout with only digital products (no shipping)

## Future Enhancements

1. Database integration for persistence
2. User authentication and authorization
3. Multiple payment methods
4. Order history and tracking
5. Inventory management system
6. Discount and promotion system
7. Multi-currency support
8. REST API endpoints
