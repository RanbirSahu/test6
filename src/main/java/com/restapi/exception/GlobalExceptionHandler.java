package com.restapi.exception;

import com.restapi.payload.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(ResourceNotFound.class)

   public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFound resourceNotFound){

    ErrorResponse errorResponse=new ErrorResponse(
            LocalDateTime.now(),
            resourceNotFound.getMessage(),
            HttpStatus.NOT_FOUND.value()
    );
    return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
   }
}
