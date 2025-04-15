package com.jacinthocaio.api_bank.response;

import com.jacinthocaio.api_bank.domain.Balance;
import com.jacinthocaio.api_bank.domain.Transaction;
import com.jacinthocaio.api_bank.domain.enums.AccountStatus;
import com.jacinthocaio.api_bank.domain.enums.AccountType;
import com.jacinthocaio.api_bank.domain.enums.HolderType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class BankAccountResponse {
    private Integer id;
    private String branch;
    private String number;
    private AccountType type;
    private String holderName;
    private String holderEmail;
    private String holderDocument;
    private HolderType holderType;
    private AccountStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Balance balance;
    private List<Transaction> transactions;

}
