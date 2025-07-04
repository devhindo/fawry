package com.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Shopping cart that manages cart items
 * Follows Single Responsibility Principle
 */
public class Cart {
    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        
        if (!product.isInStock(quantity)) {
            throw new IllegalArgumentException(
                String.format("Insufficient stock for %s. Available: %d, Requested: %d", 
                            product.getName(), product.getAvailableQuantity(), quantity));
        }

        // Check if product already exists in cart
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                throw new IllegalArgumentException("Product already in cart. Remove first to update quantity.");
            }
        }

        items.add(new CartItem(product, quantity));
    }

    public void removeItem(Product product) {
        items.removeIf(item -> item.getProduct().equals(product));
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items); // Return defensive copy
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getSubtotal() {
        return items.stream()
                   .mapToDouble(CartItem::getTotalPrice)
                   .sum();
    }

    public void clear() {
        items.clear();
    }
}
