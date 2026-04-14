package com.jad.nexaspringhelloworld.service;

public class RessourceNotFoundException extends RuntimeException {
    public RessourceNotFoundException(final String message) {
        super(message);
    }
}
