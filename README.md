# E-Commerce System Design

## Overview
This is a comprehensive e-commerce system implementation in Java that demonstrates low-level design best practices and SOLID principles.

## Running the Application

1. using `make`

```bash

$ make
Compiling Java source files...
Compilation completed successfully!
Running E-Commerce System...
==================================
=== E-COMMERCE SYSTEM DEMO ===

Test Case 1: Successful checkout
Cart contents:
- 2x Cheese @ 100.0
- 1x TV @ 500.0
- 1x Mobile scratch card @ 50.0

Customer before checkout: Customer: John Doe (Balance: 1000.00)
** Shipment notice **
1x Cheese 200g
1x Cheese 200g
1x TV 15.0kg
Total package weight 15.4kg
** Checkout receipt **
2x Cheese 200
1x TV 500
1x Mobile scratch card 50
----------------------
Subtotal 750
Shipping 159
Amount 909
Customer balance after payment: 91.00

Checkout completed successfully!
Customer after checkout: Customer: John Doe (Balance: 91.00)

==================================================

Test Case 2: Empty cart error
Expected error caught: Cart is empty

==================================================

Test Case 3: Insufficient balance error
Attempting to buy 2 TVs (1000 total)
Customer balance: 91.0
Expected error caught: Insufficient balance. Required: 1305.00, Available: 91.00

==================================================

Test Case 4: Out of stock error
Expected error caught: Insufficient stock for Biscuits. Available: 5, Requested: 10

==================================================

Test Case 5: Expired product error
Expected error caught: Product Expired Milk has expired on 2025-07-03

==================================================

Test Case 6: Digital products only (no shipping)
Cart with digital products only:
- 3x Mobile scratch card

Customer before checkout: Customer: John Doe (Balance: 91.00)
Error: Insufficient balance. Required: 150.00, Available: 91.00

```