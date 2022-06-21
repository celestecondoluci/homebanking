package com.mindhub.homebanking.servicies.implement;

import com.mindhub.homebanking.dtos.CardsDTO;
import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.repositorios.CardRepository;
import com.mindhub.homebanking.servicies.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CardServiceImplement implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public List<CardsDTO> getCardDTO() {
        return cardRepository.findAll().stream().map(card -> new CardsDTO(card)).collect(toList());
    }

    @Override
    public CardsDTO getCardDTOid(Long id) {
        return cardRepository.findById(id).map(card -> new CardsDTO(card)).orElse(null);
    }

    @Override
    public void saveCard(Card card) {
     cardRepository.save(card);
    }

    @Override
    public Card findById(Long id) {
        return cardRepository.findById(id).orElse(null);
    }
}
