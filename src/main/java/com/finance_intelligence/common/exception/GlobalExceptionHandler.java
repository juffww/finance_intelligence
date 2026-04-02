package com.finance_intelligence.common.exception;

import com.finance_intelligence.common.response.ErrorResponse;
import com.finance_intelligence.common.response.FieldError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ErrorResponse> handlingAppException (AppException appException)
    {
        ErrorCode errorCode = appException.getErrorCode();
        FieldError fieldError = new FieldError(errorCode.getField(), errorCode.getMessage());

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setFieldError(fieldError);
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlingValidationException(MethodArgumentNotValidException ex)
    {
        org.springframework.validation.FieldError fieldErr=
                ex.getBindingResult().getFieldErrors().get(0);

        FieldError fieldError = new FieldError(
                fieldErr.getField(),
                fieldErr.getDefaultMessage()
        );

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setFieldError(fieldError);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}