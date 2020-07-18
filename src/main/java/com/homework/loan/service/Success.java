package com.homework.loan.service;

public class Success<T> extends Response {
    private final T value;

    public Success(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
