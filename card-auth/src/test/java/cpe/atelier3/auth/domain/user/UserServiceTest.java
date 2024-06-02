package cpe.atelier3.auth.domain.user;

import cpe.atelier3.commons.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    public UserService userService;

    @Mock
    public IUserRepository iUserRepository;

    //TODO : Finir les 2 methodes ci dessous : pb de création/recupération de l'userTest en DB au démarrage
    @Test
    public void insertUserShouldReturnErrorMessageWhenUserAlreadyInBase() {
        //given

        //when

        //then
    }

    @Test
    public void insertUserShouldReturnOkMessageWhenUserNotInBase() {
        //given
        User userTest = new User((long) -1, "NotGoodmail", "o", "oi", 21, null);
        String expectedMessage = "ok User in base";
        when(iUserRepository.insertUser(userTest)).thenReturn(expectedMessage);
        //when
        String result = iUserRepository.insertUser(userTest);
        //then
        assertThat(result).isEqualTo(expectedMessage);
    }
}
