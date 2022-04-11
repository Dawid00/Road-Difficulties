package com.depe.roaddifficulties.controller.handler;

import com.depe.roaddifficulties.exceptions.WrongParamException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class HandlerController {
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongParamException.class)
    public String handleWrongParamException(WrongParamException exception){
        return exception.getMessage();
    }
}