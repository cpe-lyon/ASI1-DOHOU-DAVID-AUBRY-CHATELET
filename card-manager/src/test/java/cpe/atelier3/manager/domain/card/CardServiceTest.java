package cpe.atelier3.manager.domain.card;


import cpe.atelier3.commons.card.Card;
import cpe.atelier3.commons.card.exception.CardNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CardServiceTest {

    @InjectMocks
    public CardService cardService;

    @Mock
    public ICardRepository iCardRepository;

    @Test
    public void findCardByIdShouldReturnExceptionWhenCardNotFound() {
        //given
        when(iCardRepository.findById(1L)).thenReturn(Optional.empty());
        //when
        CardNotFoundException cardNotFoundException = Assertions.assertThrows(
                CardNotFoundException.class, () -> cardService.findCardById(1L)
        );
    }

    @Test
    public void getRandomStarterCardShouldShuffleFiveCard() {
        //given
        List<Card> baseCardList = List.of(
                new Card(1L, "Normal",	"oug",	"odioks",
                        "Un Pokemon tout mignon",	"hbdsb", "pokemon",	30,
                        20, 58,	0,
                        30),
                new Card(2L, "Normal",	"oug",	"odioks",
                "Un Pokemon tout mignon",	"hbdsb", "pokemon",	30,
                20, 58,	0,
                30),
                new Card(3L, "Normal",	"oug",	"odioks",
                        "Un Pokemon tout mignon",	"hbdsb", "pokemon",	30,
                        20, 58,	0,
                        30),
                new Card(4L, "Normal",	"oug",	"odioks",
                        "Un Pokemon tout mignon",	"hbdsb", "pokemon",	30,
                        20, 58,	0,
                        30),
                new Card(5L, "Normal",	"oug",	"odioks",
                        "Un Pokemon tout mignon",	"hbdsb", "pokemon",	30,
                        20, 58,	0,
                        30)
                );
        when(iCardRepository.findAll()).thenReturn(baseCardList);
        //when
        List<Card> shuffledList = cardService.getRandomStarterCards(5);
        //then
        assertThat(shuffledList).isNotEqualTo(baseCardList);
    }
}
