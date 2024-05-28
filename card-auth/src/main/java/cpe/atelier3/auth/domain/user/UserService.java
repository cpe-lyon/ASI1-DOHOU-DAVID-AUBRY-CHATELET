package cpe.atelier3.auth.domain.user;

import cpe.atelier3.commons.user.exception.UserNotFoundException;
import cpe.atelier3.commons.user.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final IUserRepository iUserRepository;

    public UserService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    public List<User> findAll(){
        return iUserRepository.findAll();
    }

    public User findUserById(Long id) throws UserNotFoundException {
        Optional<User> user = iUserRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        return user.get();
    }

    public String insertUser(User user){
        if (iUserRepository.findByEmail(user.email()).isEmpty()){
            return iUserRepository.insertUser(user);
        }
        return "user already exist, aborting";
    }
}
