package com.zenika.resanet.exception;

public class OperatorNotFoundException extends RuntimeException {
    public OperatorNotFoundException() {
        super();
    }

    public OperatorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OperatorNotFoundException(String message) {
        super(message);
    }

    public OperatorNotFoundException(Throwable cause) {
        super(cause);
    }
}
