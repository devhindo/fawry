package com.ecommerce.model;

/**
 * Digital product that doesn't expire and doesn't require shipping
 * Only extends base Product class
 */
public class DigitalProduct extends Product {

    public DigitalProduct(String name, double price, int availableQuantity) {
        super(name, price, availableQuantity);
    }

    @Override
    public String toString() {
        return String.format("%s (Digital)", super.toString());
    }
}
