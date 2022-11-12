package com.Jyoti.blog.BlogappApi.Exceptions;

public class ApiException extends RuntimeException{
    public ApiException() {
    }

    public ApiException(String message) {
        super(message);
    }
}
