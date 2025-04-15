package com.jacinthocaio.api_bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TransactionException extends ResponseStatusException {

    public TransactionException(String mensage) {
        super(HttpStatus.BAD_REQUEST, mensage);
    }
}
