package cpe.atelier3.auth.controller.user;

import cpe.atelier3.auth.domain.auth.AuthenticationService;
import cpe.atelier3.auth.domain.user.UserService;
import cpe.atelier3.commons.card.Card;
import cpe.atelier3.commons.card.exception.CardNotFoundException;
import cpe.atelier3.commons.user.User;
import cpe.atelier3.commons.user.dto.*;
import cpe.atelier3.commons.user.exception.*;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserDtoMapper userDtoMapper;

    private final UserService userService;

    private final AuthenticationService authenticationService;

    private final UserPaymentRequestDtoMapper userPaymentRequestDtoMapper;

    public UserController(UserDtoMapper userDtoMapper, UserService userService, AuthenticationService authenticationService, UserPaymentRequestDtoMapper userPaymentRequestDtoMapper) {
        this.userDtoMapper = userDtoMapper;
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.userPaymentRequestDtoMapper = userPaymentRequestDtoMapper;
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

    @PostMapping("/private/payment")
    public void processPayment(@CookieValue("token") String token, @RequestBody UserPaymentRequestDTO paymentRequestDto) throws InvalidTokenException,
            UserNotFoundException, UserSelfPaymentException, UserPaymentInsufficientBalanceException, UserPaymentImpersonationException {
        String uid = authenticationService.checkAuthentication(token);
        if (! uid.equals(paymentRequestDto.buyerId().toString())) {
            throw new UserPaymentImpersonationException();
        }
        userService.processPayment(userPaymentRequestDtoMapper.userPaymentRequestDtoToUserPaymentRequest(paymentRequestDto));
    }

    @DeleteMapping("/{uid:[0-9]+}/card/{cid:[0-9]+}")
    public void deleteCardOfUser(@CookieValue("service_token") String serviceToken, @PathVariable String uid, @PathVariable String cid) throws UserNotFoundException {
        authenticationService.checkServiceToken(serviceToken);
        userService.removeCard(Long.valueOf(uid), Long.valueOf(cid));
    }

    @PutMapping("/{uid:[0-9]+}/card/{cid:[0-9]+}")
    public void addCardToUser(@CookieValue("service_token") String serviceToken, @PathVariable String uid, @PathVariable String cid) throws UserNotFoundException,
            URISyntaxException, CardNotFoundException {
        authenticationService.checkServiceToken(serviceToken);
        userService.addCard(Long.valueOf(uid), Long.valueOf(cid));
    }

    @GetMapping("/{uid:[0-9]+}/card/{cid:[0-9]+}")
    public void getCardOfUser(@CookieValue("service_token") String serviceToken, @PathVariable String uid, @PathVariable String cid) throws CardNotOwnedException, UserNotFoundException {
        authenticationService.checkServiceToken(serviceToken);
        userService.getCardFromUser(Long.valueOf(uid), Long.valueOf(cid));
    }
}
