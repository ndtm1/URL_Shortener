package com.url.shortener.service;

public class BaseException extends RuntimeException {
    BaseException(String msg) {
        super(msg);
    }
}
