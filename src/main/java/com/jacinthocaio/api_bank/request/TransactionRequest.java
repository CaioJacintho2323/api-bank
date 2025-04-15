package com.jacinthocaio.api_bank.request;

import com.jacinthocaio.api_bank.domain.enums.AccountType;
import com.jacinthocaio.api_bank.domain.enums.HolderType;
import com.jacinthocaio.api_bank.domain.enums.TransactionType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionRequest {

    @NotNull
    private TransactionType type;
    @NotNull
    @DecimalMin(value = "0.01", message = "O valor da transação deve ser maior que zero")
    private BigDecimal amount;
    @NotNull
    private Integer bankAccountId;
    @NotNull
    @NotBlank
    private String counterpartyBankName;
    @NotNull
    @NotBlank
    private String counterpartyBranch;
    @NotNull
    @NotBlank
    private String counterpartyAccountNumber;
    @NotNull
    private AccountType counterpartyAccountType;
    @NotNull
    @NotBlank
    private String counterpartyHolderName;
    @NotNull
    private HolderType counterpartyHolderType;
    @NotNull
    @NotBlank
    private String counterpartyHolderDocument;

}
