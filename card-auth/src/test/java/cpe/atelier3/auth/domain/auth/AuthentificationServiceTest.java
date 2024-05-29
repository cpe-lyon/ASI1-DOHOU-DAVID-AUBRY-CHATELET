package cpe.atelier3.auth.domain.auth;

import cpe.atelier3.commons.auth.exception.IncorrectPasswordException;
import cpe.atelier3.commons.auth.exception.UserDoesNotExistException;
import cpe.atelier3.auth.domain.auth.jwt.JWTGenerator;
import cpe.atelier3.auth.domain.user.IUserRepository;
import cpe.atelier3.commons.card.Card;
import cpe.atelier3.commons.user.User;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static cpe.atelier3.auth.domain.auth.jwt.JWTGenerator.TTL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthentificationServiceTest {

    @InjectMocks
    public AuthenticationService authenticationService;

    @Mock
    public IUserRepository iuserRepository;

    @Mock
    public JWTGenerator jwtGenerator;

    @Test
    public void authenticateShouldThrowExceptionWhenUserIsEmpty() {
        //given
        String username = "benoit";
        String password = "oui";
        when(iuserRepository.findByUsername(username)).thenReturn(Optional.empty());
        //when

        UserDoesNotExistException thrown = Assertions.assertThrows(UserDoesNotExistException.class, () -> {
            authenticationService.authenticate(username, password);
        });
    }

    @Test
    public void authenticateShouldThrowExceptionWhenPasswordIsIncorrect() {
        //given
        String username = "benoit";
        String password = "oui";
        List<Card> list = new ArrayList<>();
        when(iuserRepository.findByUsername(username)).thenReturn(Optional.of(new User(null, "mail", "benoit",
                "non", 15.25, list)));
        //when

        IncorrectPasswordException thrown = Assertions.assertThrows(IncorrectPasswordException.class, () -> {
            authenticationService.authenticate(username, password);
        });
    }

    @Test
    public void authenticateShouldReturnCookieWhenUserHasGoodPassword() throws IncorrectPasswordException, UserDoesNotExistException {
        //given
        String username = "benoit";
        String password = "oui";
        List<Card> list = new ArrayList<>();
        when(iuserRepository.findByUsername(username)).thenReturn(Optional.of(new User(null, "mail", "benoit",
                "oui", 15.25, list)));

        Cookie c =  new Cookie("token", jwtGenerator.generate(iuserRepository.findByUsername(username).get()));
        c.setMaxAge((int) TTL/1000);
        //when
        Cookie cookie = authenticationService.authenticate(username, password);
        //then
        assertThat(cookie).isEqualTo(c);
    }
}
