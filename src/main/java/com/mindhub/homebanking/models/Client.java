package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity // genera una tabla Client en la base de datos
public class Client {
    @OneToMany(mappedBy="cliente", fetch=FetchType.EAGER)
    private Set<Account> accounts = new HashSet<>();

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private Set<Card> cards = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<ClientLoan> clientLoans = new HashSet<>();


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")   //genra el id automaticamente a cada elemento y le da un valor distinto del otro
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String firstName,lastName,mail,password;

    public Client() {} // metodo constructor vacio
    public Client(String firstName, String lastName, String mail,String password) {  //crear las propiedades del objeto cliente
        this.firstName = firstName;                                     //parametros por los cuales voy a construir a mi cliente
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
    }

    public long getId() {
        return id;
    }  /// no se llama a un set poruqe no necesitas desiganrle / cambiarle el valor

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public  String getMail(){
        return mail;
    }

    public void setMail(String mail){
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void add(Account account) {
        account.setClient(this);
        accounts.add(account);
    }

    public Set<ClientLoan> getClientLoans() {
        return clientLoans;
    }

    public void addClientLoan(ClientLoan clientLoan){
        clientLoan.setClient(this);
        clientLoan.add(clientLoan);
    }
   public List<Loan> getLoan(){
     return clientLoans.stream().map(client -> client.getLoan()).collect(Collectors.toList());
   }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    public void add(Card card) {
        card.setCard(this);
        cards.add(card);
    }
    public String getFullName(){
        return firstName + " " + lastName;
    }
}


