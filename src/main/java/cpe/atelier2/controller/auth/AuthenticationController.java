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

    @GetMapping("/login")
    public void login(@RequestBody AuthenticationRequestDTO request, HttpServletResponse response) throws IncorrectPasswordException, UserDoesNotExistException {
        String jwt = authenticationService.authenticate(request.username(), request.password());
        response.addCookie(new Cookie("token", jwt));
    }

    @PostMapping("/check")
    public void check(@CookieValue("token") String token) throws ExpiredTokenException {
        authenticationService.checkAuthentication(token);
    }
}
