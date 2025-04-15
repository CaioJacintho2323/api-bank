package com.jacinthocaio.api_bank.controller;

import com.jacinthocaio.api_bank.mapper.BalanceMapper;
import com.jacinthocaio.api_bank.response.BalanceResponse;
import com.jacinthocaio.api_bank.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/balance")
@RequiredArgsConstructor
public class BalanceController {
    private final BalanceService balanceService;
    private final BalanceMapper balanceMapper;

    @GetMapping("/{id}")
    public ResponseEntity<BalanceResponse> getBalance(@PathVariable Integer id) {
        var balanceById = balanceService.getBalanceById(id);
        var balanceResponse = balanceMapper.toBalance(balanceById);
        return ResponseEntity.ok(balanceResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BalanceResponse>> findAll() {
        var balances = balanceService.findAll();
        var listBalance = balanceMapper.toListBalance(balances);
        return ResponseEntity.ok(listBalance);
    }
}
