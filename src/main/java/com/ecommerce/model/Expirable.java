package com.ecommerce.model;

import java.time.LocalDate;

/**
 * Interface for products that can expire
 * Follows Interface Segregation Principle
 */
public interface Expirable {
    LocalDate getExpiryDate();
    boolean isExpired();
}
