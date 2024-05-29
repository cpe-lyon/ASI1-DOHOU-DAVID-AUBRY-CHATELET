package cpe.atelier3.auth.controller.auth;

import cpe.atelier3.auth.domain.auth.AuthenticationService;
import cpe.atelier3.commons.user.exception.InvalidTokenException;
import cpe.atelier3.commons.user.exception.IncorrectPasswordException;
import cpe.atelier3.commons.user.exception.UserDoesNotExistException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public void login(@RequestBody AuthenticationRequestDTO request, HttpServletResponse response) throws IncorrectPasswordException, UserDoesNotExistException {
        Cookie cookie = authenticationService.authenticate(request.username(), request.password());
        response.addCookie(cookie);
    }

    @PostMapping("/check")
    public void check(@CookieValue("token") String token) throws InvalidTokenException {
        authenticationService.checkAuthentication(token);
    }
}
