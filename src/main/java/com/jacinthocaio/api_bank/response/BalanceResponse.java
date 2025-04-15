package com.jacinthocaio.api_bank.response;

import com.jacinthocaio.api_bank.domain.BankAccount;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString
public class BalanceResponse {
    private Integer id;
    private BigDecimal availableAmount;
    private BigDecimal blockedAmount;
    private BankAccount bankAccount;
}
