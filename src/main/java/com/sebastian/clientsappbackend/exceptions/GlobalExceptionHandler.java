package com.sebastian.clientsappbackend.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sebastian.clientsappbackend.models.ErrorObjectModel;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorObjectModel> handleResourceNotFoundException(
            ResourceNotFoundException resourceNotFoundException, WebRequest webRequest) {

        ErrorObjectModel errorObject = new ErrorObjectModel();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(resourceNotFoundException.getMessage());
        errorObject.setTimeStamp(new Date());

        return new ResponseEntity<ErrorObjectModel>(errorObject, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorObjectModel> handleBadRequestException(
            MethodArgumentTypeMismatchException methodArgumentTypeMismatchException, WebRequest webRequest) {

        ErrorObjectModel errorObject = new ErrorObjectModel();

        errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorObject.setMessage(methodArgumentTypeMismatchException.getMessage());
        errorObject.setTimeStamp(new Date());

        return new ResponseEntity<ErrorObjectModel>(errorObject, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObjectModel> handleGeneralException(
            Exception exception, WebRequest webRequest) {

        ErrorObjectModel errorObject = new ErrorObjectModel();

        errorObject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorObject.setMessage(exception.getMessage());
        errorObject.setTimeStamp(new Date());

        return new ResponseEntity<ErrorObjectModel>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", new Date());
        body.put("statusCode", HttpStatus.BAD_REQUEST.value());

        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("messages", errors);

        return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
    }

}
