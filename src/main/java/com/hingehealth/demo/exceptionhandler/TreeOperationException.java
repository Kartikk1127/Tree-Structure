package com.hingehealth.demo.exceptionhandler;

public class TreeOperationException extends RuntimeException {
    public TreeOperationException(String message) {
        super(message);
    }
}