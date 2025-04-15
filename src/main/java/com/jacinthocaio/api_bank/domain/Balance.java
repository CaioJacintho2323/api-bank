package com.jacinthocaio.api_bank.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(precision = 16, scale = 2)
    private BigDecimal availableAmount;

    @Column(precision = 16, scale = 2)
    private BigDecimal blockedAmount;

    @OneToOne(mappedBy = "balance")
    @JsonBackReference
    private BankAccount bankAccount;

}
