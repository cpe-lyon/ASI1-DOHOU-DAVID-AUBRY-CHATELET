package cpe.atelier3.manager.controller.card;


import cpe.atelier3.manager.domain.card.CardService;
import cpe.atelier3.commons.card.Card;
import cpe.atelier3.commons.card.dto.CardDTO;
import cpe.atelier3.commons.card.dto.CardDtoMapper;
import cpe.atelier3.commons.card.exception.CardNotFoundException;
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
    public String addNewCard(@RequestBody Card card){
        return cardService.addNewCard(card);
    }

    @GetMapping("/{id}")
    public CardDTO findCardById(@PathVariable("id") Long id) throws CardNotFoundException {
        return cardDtoMapper.cardToCardDTO(cardService.findCardById(id));
    }
}
