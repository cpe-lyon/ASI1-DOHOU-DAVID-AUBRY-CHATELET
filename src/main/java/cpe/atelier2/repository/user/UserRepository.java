package cpe.atelier2.repository.user;

import cpe.atelier2.domain.user.IUserRepository;
import cpe.atelier2.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository implements IUserRepository {

    private final UserEntityMapper userEntityMapper;

    private final UserJpaRepository userJpaRepository;

    public UserRepository(UserEntityMapper userEntityMapper, UserJpaRepository userJpaRepository) {
        this.userEntityMapper = userEntityMapper;
        this.userJpaRepository = userJpaRepository;
    }

    public List<User> findAll(){
        List<UserEntity> userEntityList = userJpaRepository.findAll();

        return userEntityList.stream()
                .map(userEntityMapper::userEntityToUser)
                .toList();
    }

}
