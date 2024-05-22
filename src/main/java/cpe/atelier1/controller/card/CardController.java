package cpe.atelier1.controller.card;


import cpe.atelier1.domain.card.CardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    private final CardDtoMapper cardDtoMapper;

    private final CardService cardService;

    public CardController(CardDtoMapper cardDtoMapper, CardService cardService) {
        this.cardDtoMapper = cardDtoMapper;
        this.cardService = cardService;
    }

    @GetMapping("/all")
    public List<CardDTO> findAll(){
        return cardService.getAllCard().stream()
                .map(cardDtoMapper::CardToCardDTO)
                .toList();
    }
}
