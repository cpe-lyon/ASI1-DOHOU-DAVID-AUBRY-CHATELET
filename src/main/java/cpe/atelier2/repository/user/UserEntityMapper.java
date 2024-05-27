package cpe.atelier2.repository.user;

import cpe.atelier2.domain.user.User;
import cpe.atelier2.repository.card.CardEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {
    private final CardEntityMapper cardEntityMapper;

    public UserEntityMapper(CardEntityMapper cardEntityMapper) {
        this.cardEntityMapper = cardEntityMapper;
    }

    public User userEntityToUser(UserEntity userEntity) {
        return new User(userEntity.getId(), userEntity.getEmail(),
                userEntity.getUsername(), userEntity.getPassword(), userEntity.getMoney(),
                userEntity.getCardEntityList().stream()
                        .map(cardEntityMapper::cardEntityToCard)
                        .toList()
        );
    }

    public UserEntity userToUserEntity(User user){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(null);
        userEntity.setEmail(user.email());
        userEntity.setEmail(user.email());
        userEntity.setUsername(user.username());
        userEntity.setMoney(user.money());
        userEntity.setPassword(user.password());
        userEntity.setCardEntityList(user.cardList().stream()
                .map(cardEntityMapper::cardToCardEntity)
                .toList()
        );

        return userEntity;
    }
}
