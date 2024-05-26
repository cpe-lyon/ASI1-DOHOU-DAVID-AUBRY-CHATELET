package cpe.atelier2.domain.card;

import cpe.atelier2.domain.card.exceptions.CardNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CardService {

    private final ICardRepository iCardRepository;

    public CardService(ICardRepository iCardRepository) {
        this.iCardRepository = iCardRepository;
    }

    public List<Card> getAllCard(){
        return iCardRepository.findAll();
    }
  
    public Card findCardById(Long id) throws CardNotFoundException {
        Optional<Card> c = iCardRepository.findById(id);
        if (c.isEmpty()) {
            throw new CardNotFoundException();
        }
        return c.get();
    }
  
    public String addNewCard(Card card){
        if (iCardRepository.findByName(card.getName()).isPresent()){ //check existing name
            return "Card Already existing, abort";
        }

        iCardRepository.addNewCard(card);
        return "ok, card inserted";
    }

}

