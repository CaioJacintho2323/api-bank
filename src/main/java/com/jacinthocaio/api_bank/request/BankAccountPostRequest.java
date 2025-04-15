package com.jacinthocaio.api_bank.request;

import com.jacinthocaio.api_bank.domain.enums.AccountType;
import com.jacinthocaio.api_bank.domain.enums.HolderType;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class BankAccountPostRequest {
    @NotNull(message = "o campo 'branch' não pode estar nulo")
    @NotBlank(message = "o campo 'branch' não pode estar em branco")
    @Size(max = 5,message = "O número maximo de caracteres para agencia é 5.")
    private String branch;

    @NotNull(message = "o campo 'type' não pode estar nulo")
    private AccountType type;

    @NotNull(message = "o campo 'holderName' não pode estar nulo")
    @NotBlank(message = "o campo 'holderName' não pode estar em branco")
    @Size(max = 200)
    private String holderName;

    @NotNull(message = "o campo 'holderEmail' não pode estar nulo")
    @NotBlank(message = "o campo 'holderEmail' não pode estar em branco")
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "endereço de email não está em um formato válido.")
    @Size(max = 200)
    private String holderEmail;

    @NotNull(message = "o campo 'holderDocument' não pode estar nulo")
    @NotBlank(message = "o campo 'holderDocument' não pode estar em branco")
    @Pattern(regexp = "^[0-9]{11}$", message = "O campo 'holderDocument' deve conter exatamente 11 dígitos numéricos")
    private String holderDocument;

    @NotNull(message = "o campo 'holderType' não pode estar nulo")
    private HolderType holderType;


}
