package cpe.atelier2.domain.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

    public String insertUser(User user){
        if (iUserRepository.findByEmail(user.email()).isEmpty()){
            return iUserRepository.insertUser(user);
        }
        return "user already exist, aborting";
    }
}
