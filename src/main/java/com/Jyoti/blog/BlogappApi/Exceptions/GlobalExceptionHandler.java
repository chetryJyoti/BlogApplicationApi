package com.Jyoti.blog.BlogappApi.Exceptions;


import com.Jyoti.blog.BlogappApi.Payloads.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //handling resource not found exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String msg = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(msg,false);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    //handling invalid data exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String ,String> resp = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError)error).getField();
            String msg = error.getDefaultMessage();
            resp.put(fieldName,msg);
        });
        return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
    }


    //handling method not supported exception when some operations are not allowed
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public  ResponseEntity<ApiResponse> methodNotSupportedException(HttpRequestMethodNotSupportedException ex){
        String msg = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(msg,false);
        return new ResponseEntity<>(apiResponse,HttpStatus.METHOD_NOT_ALLOWED);
    }

    //handling invalid credentials exception
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> handleApiException(ApiException ex){
        String msg = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(msg,true);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
    //user already exists exception
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public  ResponseEntity<ApiResponse> userAlreadyPresentException(DataIntegrityViolationException ex){
////        String msg = ex.getMessage();
//        ApiResponse apiResponse = new ApiResponse("user already present",false);
//        return new ResponseEntity<>(apiResponse,HttpStatus.CONFLICT);
//    }

    //access denied exception handling
//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<ApiResponse> accessDeniedException(AccessDeniedException ex){
//        String msg = ex.getMessage();
//        ApiResponse apiResponse = new ApiResponse(msg,false);
//        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
//    }
}
