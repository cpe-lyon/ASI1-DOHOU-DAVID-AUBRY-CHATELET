package cpe.atelier2.repository.user;

import cpe.atelier2.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {

    public User userEntityToUser(UserEntity userEntity) {
        return new User(userEntity.getId(), userEntity.getEmail(),
                userEntity.getUsername(), userEntity.getPassword(), userEntity.getMoney());
    }
}
