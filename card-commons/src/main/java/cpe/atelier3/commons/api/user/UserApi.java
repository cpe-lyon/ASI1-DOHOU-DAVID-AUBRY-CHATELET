package cpe.atelier3.commons.api.user;

import com.google.gson.Gson;
import cpe.atelier3.commons.api.ApiUtils;
import cpe.atelier3.commons.api.auth.utils.AuthApiCookieUtils;
import cpe.atelier3.commons.api.exception.ApiNokException;
import cpe.atelier3.commons.api.user.utils.UserURIUtils;
import cpe.atelier3.commons.market.MarketBuyRequest;
import cpe.atelier3.commons.user.User;
import cpe.atelier3.commons.user.UserPaymentRequest;
import cpe.atelier3.commons.user.dto.PrivateUserDTO;
import cpe.atelier3.commons.user.dto.UserDTO;
import cpe.atelier3.commons.user.dto.UserDtoMapper;
import cpe.atelier3.commons.user.dto.UserPaymentRequestDTO;
import cpe.atelier3.commons.user.exception.CardNotOwnedException;
import cpe.atelier3.commons.user.exception.UserNotFoundException;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class UserApi {

    public static final String API_NAME = "CardAPI";

    @Autowired
    UserURIUtils userURIUtils;

    @Autowired
    UserDtoMapper userDtoMapper;

    public User findUserPrivate(String token) throws UserNotFoundException {
        Gson gson = new Gson();
        HttpRequest request;
        request = HttpRequest.newBuilder()
                .uri(userURIUtils.privateUserInfo())
                .header("Cookie", AuthApiCookieUtils.formatToken(token))
                .GET()
                .build();


        HttpResponse<String> response = null;
        try {
            response = ApiUtils.sendRequestToApi(request, API_NAME, "Get user");
        } catch (ApiNokException e) {
            if (e.getErrorCode() == 404) {
                throw new UserNotFoundException();
            }
        }

        assert response != null;
        return userDtoMapper.privateUserDtoToUser(gson.fromJson(response.body(), PrivateUserDTO.class));
    }

    public User findUserById(Long id, String token) throws UserNotFoundException {
        Gson gson = new Gson();
        HttpRequest request;
        request = HttpRequest.newBuilder()
            .uri(userURIUtils.findUserById(id))
            .header("Cookie", AuthApiCookieUtils.formatToken(token))
            .GET()
            .build();


        HttpResponse<String> response = null;
        try {
            response = ApiUtils.sendRequestToApi(request, API_NAME, "Get user");
        } catch (ApiNokException e) {
            if (e.getErrorCode() == 404) {
                throw new UserNotFoundException();
            }
        }

        assert response != null;
        return gson.fromJson(response.body(), User.class);
    }

    public void payUser(String token, UserPaymentRequestDTO userPaymentRequest) throws ResponseStatusException {
        Gson gson = new Gson();
        HttpRequest request;
        request = HttpRequest.newBuilder()
                .uri(userURIUtils.paymentToUser())
                .header("Cookie", AuthApiCookieUtils.formatToken(token))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(userPaymentRequest)))
                .build();

        HttpResponse<String> response = null;

        try {
            ApiUtils.sendRequestToApi(request, API_NAME, "Payment to user");
        } catch (ApiNokException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(e.getErrorCode()), e.getMessage());
        }
    }

    public void removeCard(String serviceToken, Long userId, Long cardId) {
        HttpRequest request;
        request = HttpRequest.newBuilder()
                .uri(userURIUtils.cardOfUserManipulation(userId, cardId))
                .header("Cookie", AuthApiCookieUtils.formatServiceToken(serviceToken))
                .DELETE()
                .build();

        try {
            ApiUtils.sendRequestToApi(request, API_NAME, "User card removal");
        } catch (ApiNokException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(e.getErrorCode()), e.getMessage());
        }
    }

    public void addCard(String serviceToken, Long userId, Long cardId) {
        HttpRequest request;
        request = HttpRequest.newBuilder()
                .uri(userURIUtils.cardOfUserManipulation(userId, cardId))
                .header("Cookie", AuthApiCookieUtils.formatServiceToken(serviceToken))
                .PUT(HttpRequest.BodyPublishers.noBody()) // Idéalement ici j'aurais du mettre un DTO avec l'id de carte à ajouter mais le temps joue en ma défaveur
                .build();

        HttpResponse<String> response = null;

        try {
            ApiUtils.sendRequestToApi(request, API_NAME, "User card addition");
        } catch (ApiNokException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(e.getErrorCode()), e.getMessage());
        }
    }

    public void getCardOfUser(String serviceToken, Long userId, Long cardId) throws CardNotOwnedException {
        HttpRequest request;
        request = HttpRequest.newBuilder()
                .uri(userURIUtils.cardOfUserManipulation(userId, cardId))
                .header("Cookie", AuthApiCookieUtils.formatServiceToken(serviceToken))
                .GET() // Idéalement ici j'aurais du mettre un DTO avec l'id de carte à ajouter mais le temps joue en ma défaveur
                .build();

        HttpResponse<String> response = null;

        try {
            ApiUtils.sendRequestToApi(request, API_NAME, "User card check");
        } catch (ApiNokException e) {
            if (e.getErrorCode() == 404) {
                throw new CardNotOwnedException();
            }
            throw new ResponseStatusException(HttpStatusCode.valueOf(e.getErrorCode()), e.getMessage());
        }
    }
}
