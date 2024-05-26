package cpe.atelier2.controller.card;


import cpe.atelier2.domain.card.Card;
import cpe.atelier2.domain.card.CardService;
import cpe.atelier2.domain.card.exception.CardNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
@CrossOrigin("*")
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
    public String addNewCard(@RequestBody Card card){
        return cardService.addNewCard(card);
    }

    @GetMapping("/{id}")
    public CardDTO findCardById(@PathVariable("id") Long id) throws CardNotFoundException {
        return cardDtoMapper.cardToCardDTO(cardService.findCardById(id));
    }
}
