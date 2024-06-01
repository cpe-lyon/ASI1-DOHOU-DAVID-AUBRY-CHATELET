package cpe.atelier3.auth.controller.user;

import cpe.atelier3.auth.domain.auth.AuthenticationService;
import cpe.atelier3.auth.domain.user.UserService;
import cpe.atelier3.commons.user.dto.*;
import cpe.atelier3.commons.user.exception.InvalidTokenException;
import cpe.atelier3.commons.user.exception.UserNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserDtoMapper userDtoMapper;

    private final UserService userService;

    private final AuthenticationService authenticationService;

    public UserController(UserDtoMapper userDtoMapper, UserService userService,  AuthenticationService authenticationService) {
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

    @GetMapping("/private")
    public PrivateUserDTO findPrivateUserDataById(@CookieValue("token") String token) throws UserNotFoundException, InvalidTokenException {
        String uid = authenticationService.checkAuthentication(token);
        return userDtoMapper.userToPrivateUserDto(userService.findUserById(Long.valueOf(uid)));
    }

    @GetMapping("/{id}")
    public PublicUserDTO findPublicUserById(@CookieValue("token") String token, @PathVariable Long id) throws UserNotFoundException, InvalidTokenException {
        authenticationService.checkAuthentication(token);
        return userDtoMapper.userToPublicUserDto(userService.findUserById(id));
    }
}
