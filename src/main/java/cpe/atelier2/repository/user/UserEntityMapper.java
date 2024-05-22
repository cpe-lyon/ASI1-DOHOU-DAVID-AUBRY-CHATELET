package cpe.atelier2.repository.user;

import cpe.atelier2.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {

    public User userEntityToUser(UserEntity userEntity) {
        return new User(userEntity.getId(), userEntity.getEmail(),
                userEntity.getUsername(), userEntity.getPassword(), userEntity.getMoney());
    }

    public UserEntity userToUserEntity(User user){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(null);
        userEntity.setEmail(user.email());
        userEntity.setUsername(user.username());
        userEntity.setMoney(user.money());
        userEntity.setPassword(user.password());

        return userEntity;
    }
}
