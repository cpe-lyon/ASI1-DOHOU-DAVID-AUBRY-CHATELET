package cpe.atelier2.domain.card;

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
  
    public Optional<Card> findCardById(Long id) { return iCardRepository.findById(id); }
  
    public String addNewCard(Card card){
        if (iCardRepository.findByName(card.getName()).isPresent()){ //check existing name
            return "Card Already existing, abort";
        }

        iCardRepository.addNewCard(card);
        return "ok, card inserted";
    }

}

