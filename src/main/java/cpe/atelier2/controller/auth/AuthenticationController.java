package cpe.atelier2.controller.auth;

import cpe.atelier2.domain.auth.AuthenticationService;
import cpe.atelier2.domain.auth.exception.ExpiredTokenException;
import cpe.atelier2.domain.auth.exception.IncorrectPasswordException;
import cpe.atelier2.domain.auth.exception.UserDoesNotExistException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    @CrossOrigin(value = "http://localhost:5173", allowCredentials = "true")
    public void login(@RequestBody AuthenticationRequestDTO request, HttpServletResponse response) throws IncorrectPasswordException, UserDoesNotExistException {
        Cookie cookie = authenticationService.authenticate(request.username(), request.password());
        cookie.setDomain("localhost");
        cookie.setHttpOnly(false);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @PostMapping("/check")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    public void check(@CookieValue("token") String token) throws ExpiredTokenException {
        authenticationService.checkAuthentication(token);
    }
}
