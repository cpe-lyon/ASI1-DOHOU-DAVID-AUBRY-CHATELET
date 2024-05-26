package cpe.atelier2.controller.user;

import cpe.atelier2.domain.user.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    private final UserDtoMapper userDtoMapper;

    private final UserService userService;

    public UserController(UserDtoMapper userDtoMapper, UserService userService) {
        this.userDtoMapper = userDtoMapper;
        this.userService = userService;
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
}
