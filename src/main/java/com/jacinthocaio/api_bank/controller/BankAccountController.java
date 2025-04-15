package com.jacinthocaio.api_bank.controller;

import com.jacinthocaio.api_bank.mapper.BankAccountMapper;
import com.jacinthocaio.api_bank.request.BankAccountPostRequest;
import com.jacinthocaio.api_bank.request.BankAccountPutRequest;
import com.jacinthocaio.api_bank.response.BankAccountResponse;
import com.jacinthocaio.api_bank.service.BankAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/bank")
@RequiredArgsConstructor
public class BankAccountController {
    private final BankAccountService bankAccountService;
    private final BankAccountMapper bankAccountMapper;


    @GetMapping("/all")
    public ResponseEntity<List<BankAccountResponse>> listAll() {
        var allAccounts = bankAccountService.findAll();
        var allAccountsResponse = bankAccountMapper.toBankAccountResponse(allAccounts);
        return ResponseEntity.ok(allAccountsResponse);
    }

    @PostMapping
    public ResponseEntity<BankAccountResponse> createAccount(@RequestBody @Valid BankAccountPostRequest dto) {
        var bankAccount = bankAccountMapper.toBankAccount(dto);
        var createAccount = bankAccountService.createAccount(bankAccount);
        var bankAccountResponse = bankAccountMapper.toBankAccountResponse(createAccount);
        return ResponseEntity.ok(bankAccountResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccountResponse> findById(@PathVariable Integer id) {
        var bankAccountById = bankAccountService.findById(id);
        var bankAccountResponse = bankAccountMapper.toBankAccountResponse(bankAccountById);
        return ResponseEntity.ok(bankAccountResponse);
    }

    @GetMapping("/agency/{branch}")
    public ResponseEntity<List<BankAccountResponse>> findByAgency(@PathVariable String branch) {
        var bankAccountByAgency = bankAccountService.findByAgency(branch);
        var bankAccountResponse = bankAccountMapper.toBankAccountResponse(bankAccountByAgency);
        return ResponseEntity.ok(bankAccountResponse);
    }

    @GetMapping("/numberAccount/{number}")
    public ResponseEntity<BankAccountResponse> findByAccountNumber(@PathVariable String number) {
        var bankAccountByAccountNumber = bankAccountService.fidnByNumber(number);
        var bankAccountResponse = bankAccountMapper.toBankAccountResponse(bankAccountByAccountNumber);
        return ResponseEntity.ok(bankAccountResponse);
    }

    @GetMapping("/document/{document}")
    public ResponseEntity<List<BankAccountResponse>> findByDocument(@PathVariable String document) {
        var bankAccountByHolderDocument = bankAccountService.findByHolderDocument(document);
        var bankAccountResponse = bankAccountMapper.toBankAccountResponse(bankAccountByHolderDocument);
        return ResponseEntity.ok(bankAccountResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAccount(@Valid @PathVariable Integer id, @RequestBody BankAccountPutRequest request) {
        var bankAccountPostRequest = bankAccountMapper.toBankAccountPostRequest(request);
        bankAccountService.updateAccountBank(id, bankAccountPostRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Integer id) {
        bankAccountService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
