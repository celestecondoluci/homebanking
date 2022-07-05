package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.AccountType;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositorios.AccountRepository;
import com.mindhub.homebanking.repositorios.ClientRepository;
import com.mindhub.homebanking.servicies.AccountService;
import com.mindhub.homebanking.servicies.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.mindhub.homebanking.utils.Utility.getNumberAccount;
import static java.util.stream.Collectors.toList;

@RestController

@RequestMapping("/api")  //ejecutar metodos con solicitudes http (url)
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/clients")
    public List<ClientDTO> getClientsDTO() {
        return clientRepository.findAll().stream().map(client -> new ClientDTO(client)).collect(toList());
    }
    @GetMapping("clients/{id}")

    public ClientDTO getClient(@PathVariable Long id) {
        return clientService.getClientDTOid(id);
    }

    @Autowired

    private PasswordEncoder passwordEncoder;


    @PostMapping("/clients")

    public ResponseEntity<Object> register(

            @RequestParam String firstName, @RequestParam String lastName,

            @RequestParam String email, @RequestParam String password) {


        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {

            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);

        }


        if (clientService.findByMail(email) != null) {
            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
        }


        clientService.saveClient(new Client(firstName, lastName, email, passwordEncoder.encode(password)));

        Client client = clientService.findByMail(email);
        String number = getNumberAccount();
        double balance = 0;
        LocalDateTime creationDate = LocalDateTime.now();
        AccountType type = AccountType.CORRIENTE;

        accountService.saveAccount(new Account(number, creationDate, balance, client, false, type));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/clients/current")
    public ClientDTO getCurrentClient(Authentication authentication) {
        return new ClientDTO(clientService.getClientCurrent(authentication));
    }

}
