package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.CardsDTO;
import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositorios.CardRepository;
import com.mindhub.homebanking.repositorios.ClientRepository;
import com.mindhub.homebanking.servicies.CardService;
import com.mindhub.homebanking.servicies.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mindhub.homebanking.models.CardType.CREDIT;
import static com.mindhub.homebanking.models.CardType.DEBIT;
import static com.mindhub.homebanking.utils.Utility.getCvv;
import static com.mindhub.homebanking.utils.Utility.getNumberCard;
import static java.util.stream.Collectors.toList;
@RestController
@RequestMapping("/api")
public class CardController {

   // @Autowired
    //private CardRepository cardRepository;
   // @Autowired
   // private ClientRepository clientRepository;

    @Autowired
    private CardService cardService;

    @Autowired
    private ClientService clientService;

    @GetMapping("/cards")
    public List<CardsDTO> getCard() {
        return cardService.getCardDTO();
    }

    @GetMapping("/cards/{id}")
    public CardsDTO getCard(@PathVariable Long id) {
        return cardService.getCardDTOid(id);
    }

    @PostMapping(path = "/clients/current/cards")
    public ResponseEntity<Object> CreateCards (Authentication authentication,
            @RequestParam CardColor color , @RequestParam CardType type
    ) {
        Client client = clientService.getClientCurrent(authentication);
        LocalDate creationCard = LocalDate.now();
        LocalDate expiresCard = LocalDate.now().plusYears(5);
        String cardholder = client.getFullName();
        String number = getNumberCard();
        int cvv = getCvv();

        List<Card> debitCard = client.getCards().stream().filter(debit ->debit.getType() == DEBIT && !debit.isDisable()).collect(Collectors.toList());

        List <Card> creditCard = client.getCards().stream().filter(credit -> credit.getType() == CardType.CREDIT && !credit.isDisable()).collect(Collectors.toList()) ;


        if (debitCard.size() >=3 && type == DEBIT) {
            return new ResponseEntity<>("You are not allowed to get another card", HttpStatus.FORBIDDEN);
        }
        if (creditCard.size() >=3 && type == CREDIT) {
            return new ResponseEntity<>("You are not allowed to get another card", HttpStatus.FORBIDDEN);
        }

        cardService.saveCard(new Card(type,color,number,cvv,creationCard,expiresCard,client,false));
        return new ResponseEntity<>(HttpStatus.CREATED);

    }


    @PatchMapping("/clients/current/cards")
    public ResponseEntity<Object> disableCard (Authentication authentication,
    @RequestParam Long id){
        Card disableCard = cardService.findById(id);
        Client client = clientService.findByMail(authentication.getName());

        if (!client.getCards().contains(disableCard)){
            return new ResponseEntity<>("This card can not be deleted", HttpStatus.FORBIDDEN);
        }

        disableCard.setDisable(true);
        cardService.saveCard(disableCard);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}
