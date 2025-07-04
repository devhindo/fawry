package com.ecommerce.model;

/**
 * Electronic product that requires shipping but doesn't expire
 * Implements only Shippable interface
 */
public class ElectronicProduct extends Product implements Shippable {
    private double weight; // in kg

    public ElectronicProduct(String name, double price, int availableQuantity, double weight) {
        super(name, price, availableQuantity);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format("%s (Weight: %.1fkg)", super.toString(), weight);
    }
}
