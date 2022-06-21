package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.TransactionsDTO;
import com.mindhub.homebanking.repositorios.AccountRepository;
import com.mindhub.homebanking.repositorios.ClientRepository;
import com.mindhub.homebanking.repositorios.TransactionsRepository;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transactions;
import com.mindhub.homebanking.models.TransactionsType;
import com.mindhub.homebanking.servicies.AccountService;
import com.mindhub.homebanking.servicies.ClientService;
import com.mindhub.homebanking.servicies.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;
@RestController
@RequestMapping("/api")
public class TransactionsController {
   // @Autowired
   // private TransactionsRepository transactionsRepository;

    //@Autowired
    //private ClientRepository clientRepository;

    //@Autowired
   // private AccountRepository accountRepository;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/transactions")
    public List<TransactionsDTO> getTransactions() {
        return transactionService.getTransactionsDTO();
    }

    @GetMapping("/transactions/{id}")

    public TransactionsDTO getTransactions(@PathVariable Long id) {

        return transactionService.getTransactionsDTOid(id);
    }

    @Transactional
    @PostMapping(path = "/clients/current/transactions")
    public ResponseEntity<Object> makeTransactions (Authentication authentication,
    @RequestParam double amount , @RequestParam String description , @RequestParam String accountNumberOrigin,
     @RequestParam String accountNumberDestination) {
        Client client = clientService.getClientCurrent(authentication);
        Account accountOrigin = accountService.findByNumber(accountNumberOrigin);
        Account accountDestination = accountService.findByNumber(accountNumberDestination);
        LocalDateTime date = LocalDateTime.now();

        if (amount <= 0.0 || description.isEmpty() || accountNumberOrigin.isEmpty() || accountNumberDestination.isEmpty()){
            return new ResponseEntity<>("missing data", HttpStatus.FORBIDDEN);
        }
        if (accountOrigin == accountDestination){
            return new ResponseEntity<>("The accounts are the same",HttpStatus.FORBIDDEN);
        }
        if (accountOrigin == null){
            return new ResponseEntity<>("This account does not exist",HttpStatus.FORBIDDEN);
        }
        if (!client.getAccounts().contains(accountOrigin)){
            return new ResponseEntity<>("You do not have the origin account",HttpStatus.FORBIDDEN);
        }
        if (accountDestination == null){
            return new ResponseEntity<>("This account does not exist", HttpStatus.FORBIDDEN);
        }
        if (accountOrigin.getBalance() < amount){
            return new ResponseEntity<>("You don't have enough money to make this transaction", HttpStatus.FORBIDDEN);
        }

        Transactions transactionDebit = new Transactions(TransactionsType.DEBITO, amount, description, date,accountOrigin,false);
        Transactions transactionsCredit = new Transactions(TransactionsType.CREDITO, amount, description,date,accountDestination,false);

        transactionDebit.setNewBalance(accountOrigin.getBalance() - amount);
        transactionsCredit.setNewBalance(accountDestination.getBalance() + amount);
        transactionDebit.setOldBalance(accountOrigin);
        transactionsCredit.setOldBalance(accountDestination);

        transactionService.saveTransaction(transactionDebit);
        transactionService.saveTransaction(transactionsCredit);


        accountOrigin.setBalance(accountOrigin.getBalance() - amount);
        accountDestination.setBalance(accountDestination.getBalance() + amount);
        accountService.saveAccount(accountOrigin);
        accountService.saveAccount(accountDestination);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //@PatchMapping("/clients/current/transactions")
   // public ResponseEntity <Object> deleteTransaction (Authentication authentication, @RequestParam Long id){

     // Account account = accountService.findById(id);
     //  Client client = clientService.findByMail(authentication.getName());

      //  if(!client.getAccounts().contains(account)){
      //      return new ResponseEntity<>("This account does not belong to you", HttpStatus.FORBIDDEN);
      //  }
      //  account.getTransactions().forEach(transaction -> {
      //      transaction.setDisable(true);
      //  transactionService.saveTransaction(transaction);
      //  });
      //  return new ResponseEntity<>(HttpStatus.CREATED);

    //}

}
