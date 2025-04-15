package com.jacinthocaio.api_bank.controller;

import com.jacinthocaio.api_bank.mapper.TransactionMapper;
import com.jacinthocaio.api_bank.request.TransactionRequest;
import com.jacinthocaio.api_bank.response.TransactionResponse;
import com.jacinthocaio.api_bank.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody @Valid TransactionRequest transactionRequest) {
        var serviceTransaction = transactionService.createTransaction(transactionRequest);
        var transactionResponse = transactionMapper.toTransactionResponse(serviceTransaction);
        return ResponseEntity.ok(transactionResponse);
    }

    @GetMapping("/document/{holderDocument}")
    public ResponseEntity<List<TransactionResponse>> findByTransactionLatest(@PathVariable String holderDocument) {
        var byTransactionLatest = transactionService.findByTransactionLatest(holderDocument);
        var listTransactionResponse = transactionMapper.toListTransactionResponse(byTransactionLatest);
        return ResponseEntity.ok(listTransactionResponse);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<TransactionResponse> findById(@PathVariable Integer id) {
        var byId = transactionService.findById(id);
        var transactionResponse = transactionMapper.toTransactionResponse(byId);
        return ResponseEntity.ok(transactionResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TransactionResponse>> findAll() {
        var all = transactionService.findAll();
        var transactionResponse = transactionMapper.toListTransactionResponse(all);
        return ResponseEntity.ok(transactionResponse);
    }


}
