package com.mindhub.homebanking.servicies.implement;

import com.mindhub.homebanking.dtos.TransactionsDTO;
import com.mindhub.homebanking.models.Transactions;
import com.mindhub.homebanking.repositorios.TransactionsRepository;
import com.mindhub.homebanking.servicies.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class TransactionServiceImplement implements TransactionService {

    @Autowired
    private TransactionsRepository transactionRepository;

    @Override
    public List<TransactionsDTO> getTransactionsDTO() {
        return transactionRepository.findAll().stream().map(transaction -> new TransactionsDTO(transaction)).collect(toList());
    }

    @Override
    public TransactionsDTO getTransactionsDTOid(Long id) {
        return transactionRepository.findById(id).map(transaction -> new TransactionsDTO(transaction)).orElse(null);
    }

    @Override
    public void saveTransaction(Transactions transaction) {
     transactionRepository.save(transaction);
    }
}
