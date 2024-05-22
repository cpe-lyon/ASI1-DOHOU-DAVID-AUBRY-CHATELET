package cpe.atelier2.controller.card;


import cpe.atelier2.domain.card.CardService;
import org.springframework.web.bind.annotation.*;

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
                .map(cardDtoMapper::cardToCardDTO)
                .toList();
    }

    @PostMapping("/add")
    @ResponseBody
    public String addNewCard(@RequestBody String data){
        return cardService.addNewCard(data);
    }
}