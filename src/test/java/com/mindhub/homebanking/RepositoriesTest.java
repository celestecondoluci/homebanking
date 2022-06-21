package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositorios.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transaction;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
    public class RepositoriesTest {

    @Autowired

    LoanRepository loanRepository;

    @Test

    public void existLoans() {

        List<Loan> loans = loanRepository.findAll();

        assertThat(loans, is(not(empty())));

    }

    @Test

    public void existPersonalLoan() {

        List<Loan> loans = loanRepository.findAll();

        assertThat(loans, hasItem(hasProperty("name", is("Personal"))));

    }

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void nameClient(){
        List<Client> clients = clientRepository.findAll();
        assertThat("Melba", equalTo("Melba"));
    }

    @Test
    public void client(){
        List<Client> clients = clientRepository.findAll();
        assertThat("Melba", not(equalTo("admin")));
    }

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void numberAccount(){
        List<Account> accounts = accountRepository.findAll();
        assertThat("VIN001", containsString("VIN001"));
    }

    @Test
    public void amountAccount(){
        List<Account> accounts = accountRepository.findAll();
        assertThat(asList(5000, 7500), everyItem(lessThan(10000)));
    }

    @Autowired
    private CardRepository cardRepository;

    @Test
    public void existCard(){
        List<Card> cards = cardRepository.findAll();
        assertThat(cards, is(not(empty())));
    }

    @Test
    public void ArrayCard(){
        List<Card> cards = cardRepository.findAll();
        assertThat(cards, isA(ArrayList.class));
    }

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Test
    public void transaction(){
        List<Transactions> transactions = transactionsRepository.findAll();
        assertThat(new Transactions(), notNullValue());
    }
    @Test
    public void transactionDescription() {
        List<Transactions> transactions = transactionsRepository.findAll();
        assertThat(transactions, hasItem(hasProperty("description", is("Netflix"))));

    }



}

