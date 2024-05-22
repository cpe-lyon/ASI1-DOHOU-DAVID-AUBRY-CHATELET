package cpe.atelier1.domain.card;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
public class CardService {

    private final ICardRepository iCardRepository;

    public CardService(ICardRepository iCardRepository) {
        this.iCardRepository = iCardRepository;
    }

    public List<Card> getAllCard(){
        return iCardRepository.findAll();
    }
}

