package com.ecommerce.exception;

/**
 * Custom exception for expired products
 */
public class ProductExpiredException extends CheckoutException {
    public ProductExpiredException(String message) {
        super(message);
    }
}
