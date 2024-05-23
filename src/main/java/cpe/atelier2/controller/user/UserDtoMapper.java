package cpe.atelier2.controller.user;

import cpe.atelier2.domain.user.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserDtoMapper {

    public UserDTO userToUserDto(User user) {
        return new UserDTO(user.id(), user.email(), user.username(), user.password(), user.money());
    }

    public User userFormDtoToUser(UserFormDTO userDTO) {
        return new User(null, userDTO.email(), userDTO.username(), userDTO.password(), 0.0, new ArrayList<>());
    }
}
