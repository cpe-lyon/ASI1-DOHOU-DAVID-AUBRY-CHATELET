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

    public String insertUser(String userData){

        Gson gson = new GsonBuilder().create();
        User userToInsert = gson.fromJson(userData, User.class);
        if (iUserRepository.findByEmail(userToInsert.email()) == null){
            return iUserRepository.insertUser(userToInsert);
        }
        return "user already exist, aborting";
    }
}
