package com.glima.moneywise.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class MoneywiseResourceException extends ResponseEntityExceptionHandler{

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String friendlyMessage = messageSource.getMessage("invalid.parameter.request", null , LocaleContextHolder.getLocale());
        return handleExceptionInternal(ex, Arrays.asList(new Error(friendlyMessage, ex.getCause().toString())), headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex,createErrorList(ex.getBindingResult()), headers, HttpStatus.BAD_GATEWAY, request);
    }

    private List<Error> createErrorList(BindingResult bindingResult) {
        List<Error> errors = new ArrayList<>();
        for(FieldError fieldError: bindingResult.getFieldErrors()){
            String friendlyMessage = messageSource.getMessage(fieldError,LocaleContextHolder.getLocale());
            errors.add(new Error(friendlyMessage, fieldError.toString()));
        }
        return errors;
    }

    public static class Error{
        private String message;
        private String technicalError;

        public Error(String friendlyMessage, String technicalError) {
            this.message = friendlyMessage;
            this.technicalError = technicalError;
        }

        public String getMessage() {
            return message;
        }

        public String getTechnicalError() {
            return technicalError;
        }
    }
}
