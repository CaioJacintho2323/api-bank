package com.jacinthocaio.api_bank.service;

import com.jacinthocaio.api_bank.domain.Balance;
import com.jacinthocaio.api_bank.repository.BalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BalanceService {
    private final BalanceRepository balanceRepository;

    public Balance getBalanceById(Integer id) {
        return balanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Balance do Id (Saldo) não encontrado"));
    }

    public List<Balance> findAll(){
        return balanceRepository.findAll();
    }

}
