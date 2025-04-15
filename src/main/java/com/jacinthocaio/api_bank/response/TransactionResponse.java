package com.jacinthocaio.api_bank.response;

import com.jacinthocaio.api_bank.domain.enums.AccountType;
import com.jacinthocaio.api_bank.domain.enums.HolderType;
import com.jacinthocaio.api_bank.domain.enums.TransactionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class TransactionResponse {
    private TransactionType type;
    private BigDecimal amount;
    private String counterpartyBankName;
    private String counterpartyBranch;
    private String counterpartyAccountNumber;
    private AccountType counterpartyAccountType;
    private String counterpartyHolderName;
    private HolderType counterpartyHolderType;
    private String counterpartyHolderDocument;
}
