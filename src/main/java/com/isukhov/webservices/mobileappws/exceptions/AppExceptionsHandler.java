package com.isukhov.webservices.mobileappws.exceptions;

import com.isukhov.webservices.mobileappws.ui.model.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class AppExceptionsHandler {

    @ExceptionHandler(UserServiceException.class)
    public ResponseEntity<Object> handleUserServiceException(UserServiceException e, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), e.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleOtherExceptions(Exception e, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), e.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
