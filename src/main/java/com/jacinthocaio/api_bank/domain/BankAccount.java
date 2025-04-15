package com.jacinthocaio.api_bank.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jacinthocaio.api_bank.domain.enums.AccountStatus;
import com.jacinthocaio.api_bank.domain.enums.AccountType;
import com.jacinthocaio.api_bank.domain.enums.HolderType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String branch;

    @Column(nullable = false, unique = true)
    private String number;

    @Enumerated(EnumType.ORDINAL)
    private AccountType type;

    @Column(nullable = false, length = 200)
    private String holderName;

    @Column(nullable = false, length = 200)
    private String holderEmail;

    @Column(nullable = false, length = 200)
    private String holderDocument;

    @Enumerated(EnumType.ORDINAL)
    private HolderType holderType;

    @Enumerated(EnumType.ORDINAL)
    private AccountStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "balance_id", referencedColumnName = "id")
    @JsonManagedReference
    private Balance balance;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Transaction> transactions = new ArrayList<>();
}
