package cpe.atelier2.repository.user;

import cpe.atelier2.domain.user.IUserRepository;
import cpe.atelier2.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository implements IUserRepository {

    private final UserEntityMapper userEntityMapper;

    private final UserJpaRepository userJpaRepository;

    public UserRepository(@Autowired UserEntityMapper userEntityMapper, @Autowired  UserJpaRepository userJpaRepository) {
        this.userEntityMapper = userEntityMapper;
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public List<User> findAll(){
        List<UserEntity> userEntityList = userJpaRepository.findAll();

        return userEntityList.stream()
                .map(userEntityMapper::userEntityToUser)
                .toList();
    }

    @Override
    public User findByEmail(String email) {
        return userEntityMapper.userEntityToUser(userJpaRepository.findByEmail(email));
    }

    @Override
    public String insertUser(User user){
        userJpaRepository.save(userEntityMapper.userToUserEntity(user));
        return "ok User in base";
    }
}
