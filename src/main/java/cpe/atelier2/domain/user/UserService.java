package cpe.atelier2.domain.user;

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

    public Optional<User> findUserById(Long id) {
        return iUserRepository.findById(id);
    }

    public String insertUser(User user){
        if (iUserRepository.findByEmail(user.email()).isEmpty()){
            return iUserRepository.insertUser(user);
        }
        return "user already exist, aborting";
    }
}
