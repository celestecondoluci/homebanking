package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.AccountType;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositorios.AccountRepository;
import com.mindhub.homebanking.repositorios.ClientRepository;
import com.mindhub.homebanking.servicies.AccountService;
import com.mindhub.homebanking.servicies.ClientService;
import com.mindhub.homebanking.servicies.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.mindhub.homebanking.utils.Utility.getNumberAccount;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/accounts")
    public List<AccountDTO> getAccounts() {
        return accountService.getAccountsDto();
    }
    @GetMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id){
        return accountService.getAccountid(id);
    }


    @PostMapping(path = "/clients/current/accounts")
    public ResponseEntity<Object> CreateAccount (Authentication authentication,
        @RequestParam AccountType type) {
        Client client = clientService.getClientCurrent(authentication);
        String number = getNumberAccount();
        double balance = 0;
        LocalDateTime creationDate = LocalDateTime.now();

        if (client.getAccounts().size() >= 3) {
            return new ResponseEntity<>("You have reached your max account limit", HttpStatus.FORBIDDEN);
        }

        accountService.saveAccount(new Account(number, creationDate, balance, client,false,type));
        return new ResponseEntity<>(HttpStatus.CREATED);

    }


    public ClientDTO getCurrentClient(Authentication authentication) {
        return new ClientDTO (clientService.getClientCurrent(authentication));}

    @PatchMapping (path = "/clients/current/accounts")
    public ResponseEntity<Object> disableAccount (Authentication authentication, @RequestParam Long id){
        Account disableAccount = accountService.findById(id);
        Client client = clientService.findByMail(authentication.getName());

        if(!client.getAccounts().contains(disableAccount)){
            return new ResponseEntity<>("You are not allowed to delete this account", HttpStatus.FORBIDDEN);
        }

        disableAccount.setDisable(true);
        accountService.saveAccount(disableAccount);
        disableAccount.getTransactions().forEach(transaction -> {
            transaction.setDisable(true);
            transactionService.saveTransaction(transaction);
        });

        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}