package com.depe.roaddifficulties.exceptions;

public class InternalServerException extends RuntimeException {
    public InternalServerException() {
        super("Server does not respond");
    }
}
