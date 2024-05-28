package cpe.atelier3.manager.domain.card;

import cpe.atelier3.manager.domain.card.exception.CardNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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

    public List<Card> getRandomStarterCards(int n) {
        List<Card> cards = new ArrayList<>(getAllCard());

        Collections.shuffle(cards);

        List<Card> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            result.add(cards.get(i));
        }

        return result;
    }

}

