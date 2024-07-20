package com.techBrains.grievance.exception;

public class MessageTranslationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MessageTranslationException(String msg) {
        super(msg);
    }
}
