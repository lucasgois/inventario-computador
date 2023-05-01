package com.github.Taylormeira.exception;

public class InventarioException extends Exception {
    public InventarioException() {
    }

    public InventarioException(String message) {
        super(message);
    }

    public InventarioException(String message, Throwable cause) {
        super(message, cause);
    }

    public InventarioException(Throwable cause) {
        super(cause);
    }
}