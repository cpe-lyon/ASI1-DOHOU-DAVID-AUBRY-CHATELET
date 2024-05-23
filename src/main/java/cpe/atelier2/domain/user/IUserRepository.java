package cpe.atelier2.domain.user;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {

    List<User> findAll();

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    String insertUser(User user);

    Optional<User> findById(Long id);
}