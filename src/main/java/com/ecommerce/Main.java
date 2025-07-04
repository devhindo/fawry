package com.ecommerce;

import com.ecommerce.exception.CheckoutException;
import com.ecommerce.model.*;
import com.ecommerce.service.*;
import java.time.LocalDate;

/**
 * Main class demonstrating the e-commerce system functionality
 * with comprehensive test cases
 */
public class Main {
    public static void main(String[] args) {
        // Initialize services
        ShippingService shippingService = new ShippingService();
        CheckoutService checkoutService = new CheckoutService(shippingService);

        // Create products
        PerishableProduct cheese = new PerishableProduct(
            "Cheese", 100, 10, LocalDate.now().plusDays(7), 0.2);
        
        PerishableProduct biscuits = new PerishableProduct(
            "Biscuits", 150, 5, LocalDate.now().plusDays(30), 0.7);
        
        ElectronicProduct tv = new ElectronicProduct(
            "TV", 500, 3, 15.0);
        
        DigitalProduct scratchCard = new DigitalProduct(
            "Mobile scratch card", 50, 100);
        
        // Create customer
        Customer customer = new Customer("John Doe", 1000.0);

        System.out.println("=== E-COMMERCE SYSTEM DEMO ===\n");
        
        // Test Case 1: Successful checkout with mixed products
        System.out.println("Test Case 1: Successful checkout");
        testSuccessfulCheckout(checkoutService, customer, cheese, tv, scratchCard);
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Test Case 2: Empty cart error
        System.out.println("Test Case 2: Empty cart error");
        testEmptyCartError(checkoutService, customer);
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Test Case 3: Insufficient balance error
        System.out.println("Test Case 3: Insufficient balance error");
        testInsufficientBalanceError(checkoutService, customer, tv);
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Test Case 4: Out of stock error
        System.out.println("Test Case 4: Out of stock error");
        testOutOfStockError(checkoutService, customer, biscuits);
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Test Case 5: Expired product error
        System.out.println("Test Case 5: Expired product error");
        testExpiredProductError(checkoutService, customer);
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Test Case 6: Only digital products (no shipping)
        System.out.println("Test Case 6: Digital products only (no shipping)");
        testDigitalProductsOnly(checkoutService, customer, scratchCard);
    }

    private static void testSuccessfulCheckout(CheckoutService checkoutService, 
                                             Customer customer, Product cheese, 
                                             Product tv, Product scratchCard) {
        try {
            Cart cart = new Cart();
            cart.addItem(cheese, 2);
            cart.addItem(tv, 1);
            cart.addItem(scratchCard, 1);
            
            System.out.println("Cart contents:");
            cart.getItems().forEach(item -> 
                System.out.println("- " + item.toString() + " @ " + item.getProduct().getPrice()));
            
            System.out.println("\nCustomer before checkout: " + customer);
            
            checkoutService.checkout(customer, cart);
            
            System.out.println("\nCheckout completed successfully!");
            System.out.println("Customer after checkout: " + customer);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void testEmptyCartError(CheckoutService checkoutService, Customer customer) {
        try {
            Cart emptyCart = new Cart();
            checkoutService.checkout(customer, emptyCart);
        } catch (CheckoutException e) {
            System.out.println("Expected error caught: " + e.getMessage());
        }
    }

    private static void testInsufficientBalanceError(CheckoutService checkoutService, 
                                                   Customer customer, Product tv) {
        try {
            Cart cart = new Cart();
            cart.addItem(tv, 2); // 2 TVs = 1000, customer only has remaining balance
            
            System.out.println("Attempting to buy 2 TVs (1000 total)");
            System.out.println("Customer balance: " + customer.getBalance());
            
            checkoutService.checkout(customer, cart);
        } catch (CheckoutException e) {
            System.out.println("Expected error caught: " + e.getMessage());
        }
    }

    private static void testOutOfStockError(CheckoutService checkoutService, 
                                          Customer customer, Product biscuits) {
        try {
            Cart cart = new Cart();
            cart.addItem(biscuits, 10); // Only 5 available
            
            checkoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Expected error caught: " + e.getMessage());
        }
    }

    private static void testExpiredProductError(CheckoutService checkoutService, Customer customer) {
        try {
            // Create expired product
            PerishableProduct expiredProduct = new PerishableProduct(
                "Expired Milk", 50, 5, LocalDate.now().minusDays(1), 1.0);
            
            Cart cart = new Cart();
            cart.addItem(expiredProduct, 1);
            
            checkoutService.checkout(customer, cart);
        } catch (CheckoutException e) {
            System.out.println("Expected error caught: " + e.getMessage());
        }
    }

    private static void testDigitalProductsOnly(CheckoutService checkoutService, 
                                              Customer customer, Product scratchCard) {
        try {
            Cart cart = new Cart();
            cart.addItem(scratchCard, 3);
            
            System.out.println("Cart with digital products only:");
            cart.getItems().forEach(item -> 
                System.out.println("- " + item.toString()));
            
            System.out.println("\nCustomer before checkout: " + customer);
            
            checkoutService.checkout(customer, cart);
            
            System.out.println("\nDigital checkout completed successfully!");
            System.out.println("Customer after checkout: " + customer);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
