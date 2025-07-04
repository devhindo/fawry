package com.ecommerce.exception;

/**
 * Custom exception for out of stock products
 */
public class OutOfStockException extends CheckoutException {
    public OutOfStockException(String message) {
        super(message);
    }
}
