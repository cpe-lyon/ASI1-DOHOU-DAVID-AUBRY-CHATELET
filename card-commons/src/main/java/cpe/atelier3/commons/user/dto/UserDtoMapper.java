package cpe.atelier3.commons.user.dto;

import cpe.atelier3.commons.api.exception.ApiNokException;
import cpe.atelier3.commons.api.card.CardApi;
import cpe.atelier3.commons.card.dto.CardDtoMapper;
import cpe.atelier3.commons.user.User;
import cpe.atelier3.commons.card.Card;
import cpe.atelier3.commons.card.entity.CardEntityMapper;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.List;

@Component
public class UserDtoMapper {

    private final CardDtoMapper cardDtoMapper;
    private final CardEntityMapper cardEntityMapper;
    private final CardApi cardApi;

    public UserDtoMapper(CardDtoMapper cardDtoMapper, CardEntityMapper cardEntityMapper, CardApi cardApi) {
        this.cardDtoMapper = cardDtoMapper;
        this.cardEntityMapper = cardEntityMapper;
        this.cardApi = cardApi;
    }

    public UserDTO userToUserDto(User user) {
        return new UserDTO(user.id(), user.email(), user.username(), user.password(), user.money(),
                user.cardList().stream()
                        .map(cardDtoMapper::cardToCardDTO)
                        .toList());
    }

    public User userFormDtoToUser(UserFormDTO userDTO) {// call api a card
        List<Card> cards = null;
        try {
            cards = cardApi.getRandomStarterCards(5);
        } catch (URISyntaxException | ApiNokException e) {
            throw new RuntimeException(e);
        }

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

    public PrivateUserDTO userToPrivateUserDto(User user) {
        return new PrivateUserDTO(user.id(), user.username(), user.email(), user.cardList(), user.money());
    }

    public User privateUserDtoToUser(PrivateUserDTO userDTO) {
        return new User(userDTO.id(), userDTO.email(), userDTO.username(), null, userDTO.money(), userDTO.cardsOfUser());
    }
}
