package cpe.atelier2.controller.user;

import cpe.atelier2.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

    public UserDTO userToUserDto(User user) {
        return new UserDTO(user.id(), user.email(), user.username(), user.password(), user.money());
    }
}
