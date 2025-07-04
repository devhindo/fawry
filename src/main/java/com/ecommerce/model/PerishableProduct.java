package com.ecommerce.model;

import java.time.LocalDate;

/**
 * Perishable product that expires and requires shipping
 * Implements both Expirable and Shippable interfaces
 */
public class PerishableProduct extends Product implements Expirable, Shippable {
    private LocalDate expiryDate;
    private double weight; // in kg

    public PerishableProduct(String name, double price, int availableQuantity, 
                           LocalDate expiryDate, double weight) {
        super(name, price, availableQuantity);
        this.expiryDate = expiryDate;
        this.weight = weight;
    }

    @Override
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format("%s (Expires: %s, Weight: %.1fkg)", 
                           super.toString(), expiryDate, weight);
    }
}
