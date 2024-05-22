package cpe.atelier2.domain.user;

import java.util.List;

public interface IUserRepository {

    List<User> findAll();

    User findByEmail(String email);

    String insertUser(User user);
}