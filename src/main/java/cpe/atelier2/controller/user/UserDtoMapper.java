package cpe.atelier2.controller.user;

import cpe.atelier2.controller.card.CardDtoMapper;
import cpe.atelier2.domain.card.Card;
import cpe.atelier2.domain.card.CardService;
import cpe.atelier2.domain.user.User;
import cpe.atelier2.repository.card.CardEntity;
import cpe.atelier2.repository.card.CardEntityMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDtoMapper {

    private final CardDtoMapper cardDtoMapper;
    private final CardEntityMapper cardEntityMapper;
    private CardService cardService;

    public UserDtoMapper(CardDtoMapper cardDtoMapper, CardEntityMapper cardEntityMapper, CardService cardService) {
        this.cardDtoMapper = cardDtoMapper;
        this.cardEntityMapper = cardEntityMapper;
        this.cardService = cardService;
    }

    public UserDTO userToUserDto(User user) {
        return new UserDTO(user.id(), user.email(), user.username(), user.password(), user.money(),
                user.cardList().stream()
                        .map(cardDtoMapper::cardToCardDTO)
                        .toList());
    }

    public User userFormDtoToUser(UserFormDTO userDTO) {
        List<Card> cards = cardService.getRandomStarterCards(5);

        return new User(null, userDTO.email(),
                userDTO.username(),
                userDTO.password(),
                0.0,
                cards
        );
    }

    public PublicUserDTO userToPublicUserDto(User user) {
        return new PublicUserDTO(user.id(), user.username());
    }
}
