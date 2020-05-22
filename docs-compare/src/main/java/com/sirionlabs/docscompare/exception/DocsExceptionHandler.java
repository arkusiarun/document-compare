package com.sirionlabs.docscompare.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DocsExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DocumentCompareException.class)
    protected ResponseEntity<ExceptionEntity> handleTotalGroupDocsException(DocumentCompareException exception) {
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        String message = exception.getMessage();
        exceptionEntity.setMessage(message);
        if (logger.isDebugEnabled()) {
            exception.printStackTrace();
            exceptionEntity.setException(exception);
        }
        logger.error(exception.getCause() != null ? exception.getCause().getLocalizedMessage() : message);
        return new ResponseEntity<>(exceptionEntity, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
