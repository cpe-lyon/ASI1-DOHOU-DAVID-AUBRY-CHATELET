package cpe.atelier2.domain.user;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {

    List<User> findAll();

    Optional<User> findByEmail(String email);

    String insertUser(User user);
}