package com.ecommerce.exception;

/**
 * Custom exception for checkout related errors
 */
public class CheckoutException extends Exception {
    public CheckoutException(String message) {
        super(message);
    }
}
