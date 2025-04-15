package com.jacinthocaio.api_bank.service;

import com.jacinthocaio.api_bank.domain.Balance;
import com.jacinthocaio.api_bank.domain.BankAccount;
import com.jacinthocaio.api_bank.domain.Transaction;
import com.jacinthocaio.api_bank.domain.enums.AccountStatus;
import com.jacinthocaio.api_bank.domain.enums.TransactionType;
import com.jacinthocaio.api_bank.exception.NotFoundException;
import com.jacinthocaio.api_bank.exception.TransactionException;
import com.jacinthocaio.api_bank.repository.BankAccountRepository;
import com.jacinthocaio.api_bank.repository.TransactionRepository;
import com.jacinthocaio.api_bank.request.TransactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final BankAccountRepository bankAccountRepository;


    @Transactional(rollbackFor = Exception.class)
    public Transaction createTransaction(TransactionRequest dto) {
        BankAccount account = bankAccountRepository.findById(dto.getBankAccountId())
                .orElseThrow(() -> new RuntimeException("Conta bancária não encontrada"));

        account.setUpdatedAt(LocalDateTime.now());

        var value = getBigDecimal(dto, account);

        Transaction tx = Transaction.builder()
                .type(dto.getType())
                .amount(value)
                .bankAccount(account)
                .counterpartyBankName(dto.getCounterpartyBankName())
                .counterpartyBranch(dto.getCounterpartyBranch())
                .counterpartyAccountNumber(dto.getCounterpartyAccountNumber())
                .counterpartyAccountType(dto.getCounterpartyAccountType())
                .counterpartyHolderName(dto.getCounterpartyHolderName())
                .counterpartyHolderType(dto.getCounterpartyHolderType())
                .counterpartyHolderDocument(dto.getCounterpartyHolderDocument())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();


        transactionRepository.save(tx);
        bankAccountRepository.save(account);

        return tx;
    }

    private static BigDecimal getBigDecimal(TransactionRequest dto, BankAccount account) {
        boolean isTransacaoComContraParte = dto.getType() == TransactionType.CREDIT || dto.getType() == TransactionType.DEBIT;

        if (isTransacaoComContraParte && account.getStatus() != AccountStatus.ACTIVE) {
            throw new TransactionException("A conta precisa estar ativa para realizar créditos ou débitos.");
        }

        Balance balance = account.getBalance();
        BigDecimal value = dto.getAmount();

        switch (dto.getType()) {
            case CREDIT -> balance.setAvailableAmount(balance.getAvailableAmount().add(value));
            case DEBIT -> {
                if (balance.getAvailableAmount().compareTo(value) < 0) {
                    throw new TransactionException("Saldo insuficiente.");
                }
                balance.setAvailableAmount(balance.getAvailableAmount().subtract(value));
            }
            case AMOUNT_HOLD -> {
                if (balance.getAvailableAmount().compareTo(value) < 0) {
                    throw new TransactionException("Saldo insuficiente para bloqueio.");
                }
                balance.setAvailableAmount(balance.getAvailableAmount().subtract(value));
                balance.setBlockedAmount(balance.getBlockedAmount().add(value));
            }
            case AMOUNT_RELEASE -> {
                if (balance.getBlockedAmount().compareTo(value) < 0) {
                    throw new TransactionException("Valor bloqueado insuficiente para desbloqueio.");
                }
                balance.setBlockedAmount(balance.getBlockedAmount().subtract(value));
                balance.setAvailableAmount(balance.getAvailableAmount().add(value));
            }
        }
        return value;
    }

    public List<Transaction> findByTransactionLatest(String holderDocument) {
        var bankAccount = bankAccountRepository.findByHolderDocument(holderDocument)
                .orElseThrow(() -> new NotFoundException("O documento não está vinculado a nenhuma conta"));

        var byHolderDocument = bankAccount.getFirst();

        return byHolderDocument.getTransactions().stream()
                .sorted(Comparator.comparing(Transaction::getCreatedAt).reversed())
                .toList();
    }

    public List<Transaction> findAll(){
        return transactionRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Transaction::getCreatedAt).reversed()).toList();
    }

    public Transaction findById(Integer id) {
        return transactionRepository.findById(id)
                .orElseThrow(() ->new NotFoundException("A transação não foi encontrada."));
    }


}




