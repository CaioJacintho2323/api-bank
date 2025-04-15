package com.jacinthocaio.api_bank.mapper;

import com.jacinthocaio.api_bank.domain.Balance;
import com.jacinthocaio.api_bank.response.BalanceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BalanceMapper {

    BalanceResponse toBalance(Balance balance);

    List<BalanceResponse> toListBalance(List<Balance> balances);
}
