package cpe.atelier2.controller.user;

import cpe.atelier2.domain.auth.AuthenticationService;
import cpe.atelier2.domain.auth.exception.ExpiredTokenException;
import cpe.atelier2.domain.user.UserService;
import cpe.atelier2.domain.user.exception.UserNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")

public class UserController {

    private final UserDtoMapper userDtoMapper;

    private final UserService userService;
    private final AuthenticationService authenticationService;

    public UserController(UserDtoMapper userDtoMapper, UserService userService, AuthenticationService authenticationService) {
        this.userDtoMapper = userDtoMapper;
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/all")
    public List<UserDTO> findAll(){
        return userService.findAll().stream()
                .map(userDtoMapper::userToUserDto)
                .toList();
    }

    @PostMapping("/register")
    @ResponseBody
    public String insertUser(@RequestBody UserFormDTO userData){
        return userService.insertUser(userDtoMapper.userFormDtoToUser(userData));
    }

    @GetMapping("/{id}")
    public PublicUserDTO findUserById(@PathVariable("id") long id) throws UserNotFoundException {
        return userDtoMapper.userToPublicUserDto(userService.findUserById(id));
    }


    @GetMapping("/private")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    public PrivateUserDTO findPrivateUserDataById(@CookieValue("token") String token) throws UserNotFoundException, ExpiredTokenException {
        String uid = authenticationService.checkAuthentication(token);
        return userDtoMapper.userToPrivateUserDto(userService.findUserById(Long.valueOf(uid)));
    }
}
