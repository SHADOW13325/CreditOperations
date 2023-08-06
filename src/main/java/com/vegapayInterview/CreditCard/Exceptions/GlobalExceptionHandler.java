package com.vegapayInterview.CreditCard.Exceptions;

import com.vegapayInterview.CreditCard.Constants.ErrorConstants;
import com.vegapayInterview.CreditCard.Models.ResponseDTO.APIErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 11:45
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {VegapayException.class})
    public ResponseEntity<APIErrorResponse> handleException(VegapayException vegapayException){
        APIErrorResponse apiErrorResponse = new APIErrorResponse(vegapayException.getCode(), vegapayException.getMessage());
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.OK);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<APIErrorResponse> handleException(ConstraintViolationException validationException){

        List<String> errorMessages = validationException.getConstraintViolations().stream().
                map(violation -> violation.getMessageTemplate()).collect(Collectors.toList());

        APIErrorResponse apiErrorResponse = new APIErrorResponse(ErrorConstants.BAD_REQUEST, errorMessages.toString() + "2");

        return new ResponseEntity<>(apiErrorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<APIErrorResponse> handleException(MethodArgumentNotValidException exception){
        List<String> errorMessages = exception.getBindingResult().getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList());
        APIErrorResponse apiErrorResponse = new APIErrorResponse(ErrorConstants.BAD_REQUEST, errorMessages.toString());
        return new ResponseEntity<>(apiErrorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<APIErrorResponse> handleException(HttpMessageNotReadableException exception){
        APIErrorResponse apiErrorResponse = new APIErrorResponse(ErrorConstants.BAD_REQUEST, "Please provide a proper request.  " + exception.getMessage());
        return new ResponseEntity<>(apiErrorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<APIErrorResponse> handleException(HttpMediaTypeNotSupportedException exception){
        APIErrorResponse apiErrorResponse = new APIErrorResponse(ErrorConstants.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(apiErrorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<APIErrorResponse> handleException(MissingServletRequestParameterException exception){
        APIErrorResponse apiErrorResponse = new APIErrorResponse(ErrorConstants.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(apiErrorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DateTimeParseException.class)
    public ResponseEntity<APIErrorResponse> handleException(DateTimeParseException exception){
        String tempMessage = ". Date must be in dd-MM-yyyy format";
        APIErrorResponse apiErrorResponse = new APIErrorResponse(ErrorConstants.BAD_REQUEST, exception.getMessage() + tempMessage);
        return new ResponseEntity<>(apiErrorResponse,HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(value = { Throwable.class})
//    public ResponseEntity<APIErrorResponse> handleException(Throwable throwable) {
//        APIErrorResponse apiErrorResponse = new APIErrorResponse(ErrorConstants.INTERNAL_SERVER_ERROR, throwable.getMessage() + "7");
//        return new ResponseEntity<>(apiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
