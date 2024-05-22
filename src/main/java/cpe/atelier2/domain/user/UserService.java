package cpe.atelier2.domain.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final IUserRepository iUserRepository;

    public UserService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    public List<User> findAll(){
        return iUserRepository.findAll();
    }
}
