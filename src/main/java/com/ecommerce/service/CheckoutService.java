package com.ecommerce.service;

import com.ecommerce.exception.*;
import com.ecommerce.model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for handling checkout process
 * Follows Single Responsibility Principle and Dependency Inversion
 */
public class CheckoutService {
    private ShippingService shippingService;

    public CheckoutService(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    public void checkout(Customer customer, Cart cart) throws CheckoutException {
        validateCheckout(customer, cart);
        
        List<Shippable> shippableItems = collectShippableItems(cart);
        double subtotal = cart.getSubtotal();
        double shippingFees = shippingService.calculateShippingCost(shippableItems);
        double totalAmount = subtotal + shippingFees;

        // Process payment
        customer.deductBalance(totalAmount);
        
        // Update product quantities
        updateProductQuantities(cart);
        
        // Process shipping
        shippingService.processShipment(shippableItems);
        
        // Print receipt
        printReceipt(cart, subtotal, shippingFees, totalAmount, customer.getBalance());
        
        // Clear cart after successful checkout
        cart.clear();
    }

    private void validateCheckout(Customer customer, Cart cart) throws CheckoutException {
        if (cart.isEmpty()) {
            throw new CheckoutException("Cart is empty");
        }

        // Check for expired products and stock availability
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            int requestedQuantity = item.getQuantity();

            // Check if product is expired
            if (product instanceof Expirable) {
                Expirable expirableProduct = (Expirable) product;
                if (expirableProduct.isExpired()) {
                    throw new ProductExpiredException(
                        String.format("Product %s has expired on %s", 
                                    product.getName(), expirableProduct.getExpiryDate()));
                }
            }

            // Check stock availability
            if (!product.isInStock(requestedQuantity)) {
                throw new OutOfStockException(
                    String.format("Insufficient stock for %s. Available: %d, Requested: %d",
                                product.getName(), product.getAvailableQuantity(), requestedQuantity));
            }
        }

        // Check customer balance
        double subtotal = cart.getSubtotal();
        List<Shippable> shippableItems = collectShippableItems(cart);
        double shippingFees = shippingService.calculateShippingCost(shippableItems);
        double totalAmount = subtotal + shippingFees;

        if (!customer.hasEnoughBalance(totalAmount)) {
            throw new InsufficientBalanceException(
                String.format("Insufficient balance. Required: %.2f, Available: %.2f",
                            totalAmount, customer.getBalance()));
        }
    }

    private List<Shippable> collectShippableItems(Cart cart) {
        List<Shippable> shippableItems = new ArrayList<>();
        
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            if (product instanceof Shippable) {
                // Add multiple instances for quantity
                for (int i = 0; i < item.getQuantity(); i++) {
                    shippableItems.add((Shippable) product);
                }
            }
        }
        
        return shippableItems;
    }

    private void updateProductQuantities(Cart cart) {
        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }
    }

    private void printReceipt(Cart cart, double subtotal, double shippingFees, 
                            double totalAmount, double remainingBalance) {
        System.out.println("** Checkout receipt **");
        
        for (CartItem item : cart.getItems()) {
            System.out.println(String.format("%dx %s %.0f", 
                item.getQuantity(), 
                item.getProduct().getName(), 
                item.getTotalPrice()));
        }
        
        System.out.println("----------------------");
        System.out.println(String.format("Subtotal %.0f", subtotal));
        System.out.println(String.format("Shipping %.0f", shippingFees));
        System.out.println(String.format("Amount %.0f", totalAmount));
        System.out.println(String.format("Customer balance after payment: %.2f", remainingBalance));
    }
}
