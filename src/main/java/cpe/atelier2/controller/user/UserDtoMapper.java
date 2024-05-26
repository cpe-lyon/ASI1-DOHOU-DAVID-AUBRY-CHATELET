package cpe.atelier2.controller.user;

import cpe.atelier2.controller.card.CardDtoMapper;
import cpe.atelier2.domain.user.User;
import cpe.atelier2.repository.card.CardEntityMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserDtoMapper {

    private final CardDtoMapper cardDtoMapper;
    private final CardEntityMapper cardEntityMapper;

    public UserDtoMapper(CardDtoMapper cardDtoMapper, CardEntityMapper cardEntityMapper) {
        this.cardDtoMapper = cardDtoMapper;
        this.cardEntityMapper = cardEntityMapper;
    }

    public UserDTO userToUserDto(User user) {
        return new UserDTO(user.id(), user.email(), user.username(), user.password(), user.money(), user.cardList().stream().map(cardEntity -> {
            return cardDtoMapper.cardToCardDTO(cardEntityMapper.cardEntityToCard(cardEntity));
        }).toList());
    }

    public User userFormDtoToUser(UserFormDTO userDTO) {
        return new User(null, userDTO.email(), userDTO.username(), userDTO.password(), 0.0, new ArrayList<>());
    }

    public PublicUserDTO userToPublicUserDto(User user) {
        return new PublicUserDTO(user.id(), user.username());
    }
}
