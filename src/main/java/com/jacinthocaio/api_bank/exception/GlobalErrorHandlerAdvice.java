package com.jacinthocaio.api_bank.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.jacinthocaio.api_bank.domain.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalErrorHandlerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<DefaultErrorMessage> handleNotFoundException(NotFoundException e) {
        var error = new DefaultErrorMessage(HttpStatus.NOT_FOUND.value(), e.getReason());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

//    DefaultHandlerExceptionResolver

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<DefaultErrorMessage> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        var error = new DefaultErrorMessage(HttpStatus.BAD_REQUEST.value(), "Usuario com esse email já existe!");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> String.format("Campo '%s': %s", error.getField(), error.getDefaultMessage()))
                .toList();

        var errorResponse = new DefaultErrorMessage(HttpStatus.BAD_REQUEST.value(), "Erro de validação %s".formatted(errors));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<DefaultErrorMessage> handleTransactionException(TransactionException e) {
        var error = new DefaultErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getReason());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<Map<String, String>> handleEnumError(InvalidFormatException ex) {
        Map<String, String> error = Map.of(
                "error", "%s Bad Request".formatted(HttpStatus.BAD_REQUEST.value()),
                "valor_enviado", ex.getValue().toString(),
                "valores_aceitos", Arrays.toString(ex.getTargetType().getEnumConstants())
        );
        return ResponseEntity.badRequest().body(error);
    }

}
