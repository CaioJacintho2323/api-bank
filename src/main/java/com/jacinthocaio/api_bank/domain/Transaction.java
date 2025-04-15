package com.jacinthocaio.api_bank.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jacinthocaio.api_bank.domain.enums.AccountType;
import com.jacinthocaio.api_bank.domain.enums.HolderType;
import com.jacinthocaio.api_bank.domain.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(nullable = false)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "bankAccountId", nullable = false)
    @JsonIgnoreProperties("transactions")
    private BankAccount bankAccount;

    @Column(nullable = false)
    private String counterpartyBankName;

    @Column(nullable = false)
    private String counterpartyBranch;

    @Column(nullable = false)
    private String counterpartyAccountNumber;

    @Enumerated(EnumType.STRING)
    private AccountType counterpartyAccountType;

    @Column(nullable = false)
    private String counterpartyHolderName;

    @Enumerated(EnumType.STRING)
    private HolderType counterpartyHolderType;

    @Column(nullable = false)
    private String counterpartyHolderDocument;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

}
