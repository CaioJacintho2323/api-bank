package com.jacinthocaio.api_bank.mapper;

import com.jacinthocaio.api_bank.domain.Transaction;
import com.jacinthocaio.api_bank.response.TransactionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransactionMapper {
    TransactionResponse toTransactionResponse(Transaction transaction);

    List<TransactionResponse> toListTransactionResponse(List<Transaction> transactions);

}
