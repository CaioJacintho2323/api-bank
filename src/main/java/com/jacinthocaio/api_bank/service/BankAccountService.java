package com.jacinthocaio.api_bank.service;

import com.jacinthocaio.api_bank.domain.Balance;
import com.jacinthocaio.api_bank.domain.BankAccount;
import com.jacinthocaio.api_bank.domain.enums.AccountStatus;
import com.jacinthocaio.api_bank.exception.EmailAlreadyExistsException;
import com.jacinthocaio.api_bank.exception.NotFoundException;
import com.jacinthocaio.api_bank.exception.TransactionException;
import com.jacinthocaio.api_bank.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    @Transactional(rollbackFor = Exception.class)
    public BankAccount createAccount(BankAccount bankAccount) {

        findByAccount(bankAccount.getHolderDocument()).ifPresent(exists -> {
            if (exists.getHolderType() == bankAccount.getHolderType()) {
                throw new TransactionException("Uma conta já está criada com esse holderType");
            }
        });


        assertEmailDoesNotExist(bankAccount.getHolderEmail());

        var balance = Balance.builder()
                .availableAmount(BigDecimal.ZERO)
                .blockedAmount(BigDecimal.ZERO)
                .build();

        bankAccount.setBranch(bankAccount.getBranch().toLowerCase());
        bankAccount.setNumber(String.valueOf(System.currentTimeMillis()).substring(5, 10));
        bankAccount.setStatus(AccountStatus.ACTIVE);
        bankAccount.setBalance(balance);

        balance.setBankAccount(bankAccount);

        return bankAccountRepository.save(bankAccount);
    }

    public void updateAccountBank(Integer id, BankAccount updatedAccount) {
        var existingAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Conta não encontrada"));

        assertEmailDoesNotExist(updatedAccount.getHolderEmail(), id);

        existingAccount.setHolderEmail(updatedAccount.getHolderEmail());
        existingAccount.setStatus(updatedAccount.getStatus());

        bankAccountRepository.save(existingAccount);
    }

    @Transactional()
    public List<BankAccount> findAll() {
        return bankAccountRepository.findAllAccounts();
    }

    public BankAccount findById(Integer id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Conta não encontrada"));
    }

    public List<BankAccount> findByAgency(String branch) {
        return bankAccountRepository.findByBranchIgnoreCase(branch)
                .orElseThrow(() -> new NotFoundException("Erro ao buscar essa agencia"));
    }

    public List<BankAccount> findByHolderDocument(String holderDocument) {
        return bankAccountRepository.findByHolderDocument(holderDocument)
                .orElseThrow(() -> new NotFoundException("Erro ao buscar a conta vinculado a esse documento"));
    }

    public Optional<BankAccount> findByAccount(String holderDocument) {
        return bankAccountRepository.findByHolderDocumentAndTypeTrue(holderDocument);
    }

    public BankAccount fidnByNumber(String accountNumber) {
        return bankAccountRepository.findByNumber(accountNumber)
                .orElseThrow(() -> new NotFoundException("Erro ao buscar esse numero de conta"));
    }

    public void deleteById(Integer id) {
        var byId = findById(id);
        bankAccountRepository.delete(byId);
    }

    public void assertEmailDoesNotExist(String email) {
        bankAccountRepository.findByHolderEmail(email).ifPresent(this::throwEmailExistsException);
    }

    public void assertEmailDoesNotExist(String email, Integer id) {
        bankAccountRepository.findByHolderEmailAndIdNot(email, id)
                .ifPresent(this::throwEmailExistsException);
    }

    private void throwEmailExistsException(BankAccount bankAccount) {
        throw new EmailAlreadyExistsException("Email %s já existe".formatted(bankAccount.getHolderEmail()));
    }


}
