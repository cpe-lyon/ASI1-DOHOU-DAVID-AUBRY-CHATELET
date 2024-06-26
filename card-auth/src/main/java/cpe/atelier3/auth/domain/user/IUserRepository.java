package cpe.atelier3.auth.domain.user;

import cpe.atelier3.commons.user.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {

    List<User> findAll();

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    String insertUser(User user);

    Optional<User> findById(Long id);

}