package com.depe.roaddifficulties.exceptions;
public class WrongParamException extends RuntimeException{
    public WrongParamException(String message) {
        super(message);
    }
}