package com.binary.hospital.ExceptionHandl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {

   @ResponseStatus(HttpStatus.BAD_REQUEST)
   @org.springframework.web.bind.annotation.
           ExceptionHandler(MethodArgumentNotValidException.class)
   Map<String, String> exceptionHandler(MethodArgumentNotValidException exception) {
      Map<String, String> errors = new HashMap<>();
      exception.getBindingResult().getFieldErrors().forEach(error -> {
         errors.put(error.getField(), error.getDefaultMessage());
      });
      return errors;


   }
}







    /*
   @ResponseStatus(HttpStatus.NOT_FOUND)
   @ExceptionHandler

   public ResponseEntity<Object> handllerStudentnotFoundExeption(StudentNotFoundExceptio studentNotFoundExceptio)
   {
      Map<String,String> errorMap= new HashMap<>();
      errorMap.put("message",StudentNotFoundExceptio.getMessage());
      errorMap.put("timestamp",new Data().toString());
      errorMap.put("httpstatus",HttpStatus.NOT_FOUND.toString());

      return new ResponseEntity<>(errorMap,HttpStatus.NOT_FOUND);
   }

   */

