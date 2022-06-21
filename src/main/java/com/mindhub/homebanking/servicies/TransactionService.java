package com.mindhub.homebanking.servicies;

import com.mindhub.homebanking.dtos.TransactionsDTO;
import com.mindhub.homebanking.models.Transactions;


import java.util.List;

public interface TransactionService {

    List<TransactionsDTO> getTransactionsDTO();

    public TransactionsDTO getTransactionsDTOid(Long id);

    void saveTransaction(Transactions transaction);
}
