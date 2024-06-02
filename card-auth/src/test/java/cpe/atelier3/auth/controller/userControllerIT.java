package cpe.atelier3.auth.controller;

import cpe.atelier3.auth.AuthApplicationIT;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class userControllerIT extends AuthApplicationIT {

    @Test
    public void findAllShouldReturnAllUsersDTO() {
        // TODO : DO IT TEST WITH AN BASE USER DB
    }
}
