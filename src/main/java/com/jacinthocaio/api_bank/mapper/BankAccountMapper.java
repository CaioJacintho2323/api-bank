package com.jacinthocaio.api_bank.mapper;

import com.jacinthocaio.api_bank.domain.BankAccount;
import com.jacinthocaio.api_bank.request.BankAccountPostRequest;
import com.jacinthocaio.api_bank.request.BankAccountPutRequest;
import com.jacinthocaio.api_bank.response.BankAccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BankAccountMapper {

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    BankAccount toBankAccount(BankAccountPostRequest bankAccountRequest);


    List<BankAccountResponse> toBankAccountResponse(List<BankAccount> bankAccount);

    BankAccountResponse toBankAccountResponse(BankAccount bankAccount);

    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    BankAccount toBankAccountPostRequest(BankAccountPutRequest bankAccountPutRequest);

}
