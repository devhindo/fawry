package com.ecommerce.exception;

/**
 * Custom exception for insufficient balance
 */
public class InsufficientBalanceException extends CheckoutException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
