package com.jacinthocaio.api_bank.repository;

import com.jacinthocaio.api_bank.domain.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository <BankAccount, Integer> {
    Optional<BankAccount> findByNumber(String number);
    Optional<List<BankAccount>> findByBranchIgnoreCase (String branch);
    Optional<List<BankAccount>> findByHolderDocument (String holderDocument);
    Optional<BankAccount> findByHolderEmail(String holderEmail);
    Optional<BankAccount> findByHolderEmailAndIdNot(String holderEmail,Integer id);
    Optional<BankAccount> findByHolderDocumentAndTypeTrue (String holderDocument);

    @Query("SELECT DISTINCT ba FROM BankAccount ba LEFT JOIN FETCH ba.transactions")
    List<BankAccount> findAllAccounts();
}
