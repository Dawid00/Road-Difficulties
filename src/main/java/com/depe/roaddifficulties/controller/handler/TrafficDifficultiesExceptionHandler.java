package com.depe.roaddifficulties.controller.handler;

import com.depe.roaddifficulties.exceptions.InternalServerException;
import com.depe.roaddifficulties.exceptions.WrongParamException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TrafficDifficultiesExceptionHandler {


    @ExceptionHandler(WrongParamException.class)
    public ResponseEntity<String> handleWrongParamException(WrongParamException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<String> handleInternalServerException(InternalServerException exception){
        return ResponseEntity.internalServerError().body(exception.getMessage());
    }

}