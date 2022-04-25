package com.depe.roaddifficulties.traffic_difficulty;

import com.depe.roaddifficulties.exceptions.InternalServerException;
import com.depe.roaddifficulties.exceptions.WrongParamException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class TrafficDifficultiesExceptionHandler {


    @ExceptionHandler(WrongParamException.class)
    ResponseEntity<String> handleWrongParamException(WrongParamException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(InternalServerException.class)
    ResponseEntity<String> handleInternalServerException(InternalServerException exception){
        return ResponseEntity.internalServerError().body(exception.getMessage());
    }

}