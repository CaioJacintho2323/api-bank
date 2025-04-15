package com.jacinthocaio.api_bank.request;

import com.jacinthocaio.api_bank.domain.enums.AccountStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class BankAccountPutRequest {
    @NotNull
    private Integer id;

    @NotNull(message = "o campo 'status' não pode estar nulo")
    @NotBlank
    private AccountStatus status;

    @NotNull(message = "o campo 'holderEmail' não pode estar nulo")
    @NotBlank(message = "o campo 'holderEmail' não pode estar em branco")
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "endereço de email não está em um formato válido.")
    private String holderEmail;


}
