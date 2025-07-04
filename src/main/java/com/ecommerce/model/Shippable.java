package com.ecommerce.model;

/**
 * Interface for products that can be shipped
 * Follows Interface Segregation Principle
 */
public interface Shippable {
    String getName();
    double getWeight(); // in kg
}
