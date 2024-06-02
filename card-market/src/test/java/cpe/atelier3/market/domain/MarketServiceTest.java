package cpe.atelier3.market.domain;

import cpe.atelier3.commons.card.Card;
import cpe.atelier3.commons.card.exception.CardNotFoundException;
import cpe.atelier3.commons.market.MarketSellProposal;
import cpe.atelier3.commons.market.exception.MarketSellProposalAlreadyExistsException;
import cpe.atelier3.commons.market.exception.MarketSellProposalCardNotOwnedException;
import cpe.atelier3.commons.user.User;
import cpe.atelier3.market.domain.market.IMarketRepository;
import cpe.atelier3.market.domain.market.MarketService;
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
public class MarketServiceTest {

    @InjectMocks
    public MarketService marketService;

    @Mock
    public IMarketRepository iMarketRepository;

    @Test
    public void findMarketSellProposalForUserAndCardShouldReturnMarketSellProposal() {

    }

    @Test
    public void findMarketSellProposalForUserAndCardShouldThrowExceptionWhenEmptyFind() {

    }

    @Test
    public void sellShouldThrowMarketSellProposalCardNotOwnedException() {
        //given
        MarketSellProposal givenMarket = new MarketSellProposal(3L, new Card(3L, "Normal", "oug",
                "odioks",
                "Un Pokemon tout mignon", "hbdsb", "pokemon", 30,
                20, 58, 0, 30),
                new User(3L, "mail", "benoit",
                        "oui", 18.2, new ArrayList<>()), 25);

        //when
        MarketSellProposalCardNotOwnedException cardNotFoundException = Assertions.assertThrows(
                MarketSellProposalCardNotOwnedException.class, () -> marketService.sell(givenMarket)
        );
    }

    @Test
    public void sellShouldThrowMarketSellProposalAlreadyExistsException() {
        //given
        List<Card> givenList = List.of(new Card(3L, "Normal", "oug",
                "odioks",
                "Un Pokemon tout mignon", "hbdsb", "pokemon", 30,
                20, 58, 0, 30));
        MarketSellProposal givenMarket = new MarketSellProposal(3L, new Card(3L, "Normal", "oug",
                "odioks",
                "Un Pokemon tout mignon", "hbdsb", "pokemon", 30,
                20, 58, 0, 30),
                new User(3L, "mail", "benoit",
                        "oui", 18.2, givenList), 25);

        when(iMarketRepository.findMarketSellProposalBySellerIdAndCardId(3L, 3L))
                .thenReturn(Optional.of(new MarketSellProposal(1L, new Card(1L, "Normal", "oug",
                        "odioks",
                        "Un Pokemon tout mignon", "hbdsb", "pokemon", 30,
                        20, 58, 0, 30),
                        new User(1L, "mail", "benoit",
                                "oui", 18.2, givenList), 25)));
        //when
        MarketSellProposalAlreadyExistsException cardNotFoundException = Assertions.assertThrows(
                MarketSellProposalAlreadyExistsException.class, () -> marketService.sell(givenMarket)
        );
    }
}
