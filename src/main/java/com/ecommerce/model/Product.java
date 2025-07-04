package com.ecommerce.model;

/**
 * Abstract base class for all products
 * Follows Single Responsibility Principle
 */
public abstract class Product {
    protected String name;
    protected double price;
    protected int availableQuantity;

    public Product(String name, double price, int availableQuantity) {
        this.name = name;
        this.price = price;
        this.availableQuantity = availableQuantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void reduceQuantity(int quantity) {
        if (quantity > availableQuantity) {
            throw new IllegalArgumentException("Cannot reduce quantity by more than available");
        }
        this.availableQuantity -= quantity;
    }

    public boolean isInStock(int requestedQuantity) {
        return availableQuantity >= requestedQuantity;
    }

    @Override
    public String toString() {
        return String.format("%s (Price: %.2f, Available: %d)", name, price, availableQuantity);
    }
}
