package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositorios.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@SpringBootApplication
public class HomebankingApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionsRepository transactionsRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository, CardRepository cardRepository) {
		return (args) -> {
			// save a couple of customers
			Client client1 = new Client("Melba", "Morel", "melba@mindhub.com", passwordEncoder.encode("melba"));
			Client client2 = new Client("Mabel", "Muriel", "muriel@mindhub.com", passwordEncoder.encode("mabel"));
			Client admin1 = new Client("Admin","admin","admin@bank.com", passwordEncoder.encode("admin"));
			clientRepository.save(client1);
			clientRepository.save(client2);
			clientRepository.save(admin1);

			LocalDateTime today = LocalDateTime.now();
			Account account1 = new Account("VIN001", LocalDateTime.now(), 5000, client1, false,AccountType.AHORRO);
			Account account2 = new Account("VIN002", today.plusDays(1), 7500, client1,false,AccountType.CORRIENTE);
			Account account3 = new Account("VIN003", today.plusDays(2), 6000, client2,false,AccountType.AHORRO);
			Account account4 = new Account("VIN004", LocalDateTime.now(), 4000, client2,false,AccountType.CORRIENTE);
			accountRepository.save(account1);
			accountRepository.save(account2);
			accountRepository.save(account3);
			accountRepository.save(account4);

			Transactions transactions1 = new Transactions(TransactionsType.DEBITO,540,"Netflix",LocalDateTime.now(),account1,false);
            Transactions transactions2 = new Transactions(TransactionsType.DEBITO, 279, "Spotify",LocalDateTime.now(),account1,false);
			Transactions transactions3 = new Transactions(TransactionsType.CREDITO,270, "Lucas Rodirguez te transfirio", LocalDateTime.now(),account1,false);
			Transactions transactions4 = new Transactions(TransactionsType.DEBITO,350,"Compra Farmacia Boulevard", LocalDateTime.now(),account1,false);
			Transactions transactions5 = new Transactions(TransactionsType.CREDITO,840,"Daniela Martinez te transfirio",LocalDateTime.now(),account1,false);

			transactions1.setOldBalance(account1);
            transactions2.setOldBalance(account1);
            transactions3.setOldBalance(account1);
            transactions4.setOldBalance(account1);
			transactions5.setOldBalance(account1);

			transactionsRepository.save(transactions1);
			transactionsRepository.save(transactions2);
			transactionsRepository.save(transactions3);
			transactionsRepository.save(transactions4);
			transactionsRepository.save(transactions5);

			Transactions transactions6 = new Transactions(TransactionsType.DEBITO, 745,"Transferencia enviada a Valentina Aguilera",LocalDateTime.now(),account2,false);
			Transactions transactions7 = new Transactions(TransactionsType.CREDITO, 159.5, "Monica Gomez te transfirio",today.plusDays(3),account2,false);
			Transactions transactions8 = new Transactions(TransactionsType.DEBITO,319, "Amazon Prime Video",today.plusDays(1),account2,false);
			Transactions transactions9 = new Transactions(TransactionsType.DEBITO, 540,"Uber",LocalDateTime.now(),account2,false);
			Transactions transactions10 = new Transactions(TransactionsType.CREDITO,270,"Martina Espinoza te transfirio",LocalDateTime.now(),account2,false);

			transactions6.setOldBalance(account2);
            transactions7.setOldBalance(account2);
            transactions8.setOldBalance(account2);
            transactions9.setOldBalance(account2);
			transactions10.setOldBalance(account2);

			transactionsRepository.save(transactions6);
			transactionsRepository.save(transactions7);
			transactionsRepository.save(transactions8);
			transactionsRepository.save(transactions9);
			transactionsRepository.save(transactions10);

			Loan loan1 = new Loan("Hipotecario", 500000, List.of(12,24,36,48,60), 1.20);
            Loan loan2 = new Loan("Personal",10000, List.of(6,12,24),1.10);
			Loan loan3 = new Loan("Automotriz", 300000,List.of(6,12,24,36),1.30);
            loanRepository.save(loan1);
			loanRepository.save(loan2);
			loanRepository.save(loan3);

			ClientLoan clientLoan1 = new ClientLoan(40000,60,client1,loan1,40000 * loan1.getPercentage() );
			ClientLoan clientLoan2 = new ClientLoan(50000,12,client1,loan2,50000 * loan2.getPercentage());
			ClientLoan clientLoan3 = new ClientLoan(100000,24,client2,loan2,100000 * loan2.getPercentage());
			ClientLoan clientLoan4 = new ClientLoan(200000,36,client2,loan3,200000 * loan3.getPercentage());
			clientLoanRepository.save(clientLoan1);
			clientLoanRepository.save(clientLoan2);
			clientLoanRepository.save(clientLoan3);
			clientLoanRepository.save(clientLoan4);

			Card card1 = new Card(CardType.DEBIT, CardColor.GOLD,"2001 4598 1037 1422",234,LocalDate.now(),LocalDate.now().plusYears(5), client1,false);
			Card card2 = new Card(CardType.CREDIT, CardColor.TITANIUM,"2503 1903 6782 1967", 903,LocalDate.now(),LocalDate.now().plusYears(5),client1,false);
			Card card3 = new Card(CardType.CREDIT,CardColor.SILVER,"8934 8567 8123 1903",250,LocalDate.now(),LocalDate.now().plusYears(5), client2,false);
			cardRepository.save(card1);
			cardRepository.save(card2);
			cardRepository.save(card3);
			Card cardprueba = new Card(CardType.CREDIT,CardColor.SILVER ,"4500 3546 8569 1435",123,LocalDate.now(),LocalDate.now().minusYears(2),client1,false);
            cardRepository.save(cardprueba);
		};
	}

}
