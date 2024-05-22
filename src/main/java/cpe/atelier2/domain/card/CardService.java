package cpe.atelier2.domain.card;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


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

    public String addNewCard(String data){

        Gson gson = new GsonBuilder().create();
        Card cardToInsert = gson.fromJson(data, Card.class);

        if (iCardRepository.findByName(cardToInsert.getName()) == null){ //check existing name
            return "Card Already existing, abort";
        }

        iCardRepository.addNewCard(cardToInsert);
        return "ok, card inserted";

    }

}

