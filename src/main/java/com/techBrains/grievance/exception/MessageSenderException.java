package com.techBrains.grievance.exception;

public class MessageSenderException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MessageSenderException(String msg) {
        super(msg);
    }
}
