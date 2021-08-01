package org.kds.exceptions;

public class InvalidPaymentEventException extends RuntimeException {
    public InvalidPaymentEventException() {
    }

    public InvalidPaymentEventException(String message) {
        super(message);
    }
}
