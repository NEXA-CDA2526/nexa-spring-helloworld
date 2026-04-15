package com.jad.nexaspringhelloworld.service;

public class ServiceOperationException extends RuntimeException {
    public ServiceOperationException(final String message) {
        super(message);
    }
}
