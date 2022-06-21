package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.LoanApplicationDTO;
import com.mindhub.homebanking.dtos.LoanDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositorios.*;

import com.mindhub.homebanking.servicies.AccountService;
import com.mindhub.homebanking.servicies.ClientService;
import com.mindhub.homebanking.servicies.LoanService;
import com.mindhub.homebanking.servicies.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;


@RestController
@RequestMapping("/api")
public class LoanController {
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private ClientLoanRepository clientLoanRepository;

    @Autowired
    private LoanService loanService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/loans")
    public List<LoanDTO> getLoans() {
        return loanService.getLoansDTO();
    }

    @GetMapping("/loans/{id}")

    public LoanDTO getLoans(@PathVariable Long id) {

        return loanService.getLoansDTOid(id);

    }

    @Transactional
    @PostMapping(path = "/loans")
    public ResponseEntity<Object> solicitationLoan (Authentication authentication,
    @RequestBody LoanApplicationDTO loanApplicationDTO) {
        Client client = clientService.findByMail(authentication.getName());
        Loan loan = loanService.findById(loanApplicationDTO.getIdLoan());
        Account accountLoan = accountService.findByNumber(loanApplicationDTO.getAccountNumberDestination());
        int loan20porciento = (int) (loanApplicationDTO.getAmount() * 1.20);

        if (loanApplicationDTO.getIdLoan() <= 0 || loanApplicationDTO.getAmount() <= 0 ||
                loanApplicationDTO.getPayments() <= 0 || loanApplicationDTO.getAccountNumberDestination().isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        if (loan == null) {
            return new ResponseEntity<>("This loan does not exist", HttpStatus.FORBIDDEN);
        }
        if (loanApplicationDTO.getAmount() > loan.getMaxAmount()) {
            return new ResponseEntity<>("Amount is not available", HttpStatus.FORBIDDEN);
        }
        if (!loan.getPayments().contains(loanApplicationDTO.getPayments())) {
            return new ResponseEntity<>("Paymets not available", HttpStatus.FORBIDDEN);
        }
        if (accountLoan == null) {
            return new ResponseEntity<>("This account does not exist", HttpStatus.FORBIDDEN);

        }

        Transactions loanTransaction = new Transactions(TransactionsType.CREDITO, loanApplicationDTO.getAmount(), loan.getName() + " " + "Loan approved", LocalDateTime.now(), accountLoan,false);

        loanTransaction.setOldBalance(accountLoan);
        transactionService.saveTransaction(loanTransaction);

        ClientLoan clientLoan = new ClientLoan(loan20porciento, loanApplicationDTO.getPayments(), client, loan,loan.getPercentage());
        loanService.saveClientLoan(clientLoan);

        accountLoan.setBalance(accountLoan.getBalance() + loanApplicationDTO.getAmount());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/loans")
    public ResponseEntity<Object> createLoan (Authentication authentication,
     @RequestParam String name, @RequestParam int maxAmount,
     @RequestParam ArrayList<Integer> payments, @RequestParam double percentage){

        if (name.isEmpty() || maxAmount == 0 || payments.isEmpty() || percentage == 0) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        Loan loan = new Loan(name,maxAmount,payments,percentage);
        loanService.saveLoan(loan);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
