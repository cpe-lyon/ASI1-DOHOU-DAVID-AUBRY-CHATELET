package cpe.atelier3.auth.domain.user;

import cpe.atelier3.commons.api.card.CardApi;
import cpe.atelier3.commons.api.user.UserApi;
import cpe.atelier3.commons.card.Card;
import cpe.atelier3.commons.card.exception.CardNotFoundException;
import cpe.atelier3.commons.user.UserPaymentRequest;
import cpe.atelier3.commons.user.dto.UserDtoMapper;
import cpe.atelier3.commons.user.exception.CardNotOwnedException;
import cpe.atelier3.commons.user.exception.UserNotFoundException;
import cpe.atelier3.commons.user.User;
import cpe.atelier3.commons.user.exception.UserPaymentInsufficientBalanceException;
import cpe.atelier3.commons.user.exception.UserSelfPaymentException;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final IUserRepository iUserRepository;
    private final UserDtoMapper userDtoMapper;
    private final CardApi cardApi;

    public UserService(IUserRepository iUserRepository, UserApi userApi, UserDtoMapper userDtoMapper, CardApi cardApi) {
        this.iUserRepository = iUserRepository;
        this.userDtoMapper = userDtoMapper;
        this.cardApi = cardApi;
    }

    public List<User> findAll(){
        return iUserRepository.findAll();
    }

    public User findUserById(Long id) throws UserNotFoundException {
        Optional<User> user = iUserRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        return user.get();
    }

    public String insertUser(User user){
        if (iUserRepository.findByEmail(user.email()).isEmpty()){
            return iUserRepository.insertUser(user);
        }
        return "user already exist, aborting";
    }

    public void updateUser(User user) {
        iUserRepository.insertUser(user);
    }

    public void processPayment(UserPaymentRequest userPaymentRequest) throws UserNotFoundException,
            UserSelfPaymentException,
            UserPaymentInsufficientBalanceException {
        User buyer = findUserById(userPaymentRequest.buyerId());
        User seller = findUserById(userPaymentRequest.sellerId());

        if (buyer.equals(seller)) {
            throw new UserSelfPaymentException();
        }

        if (buyer.money() < userPaymentRequest.withdrawalAmount()) {
            throw new UserPaymentInsufficientBalanceException();
        }

        buyer.setMoney(buyer.money() - userPaymentRequest.withdrawalAmount());
        seller.setMoney(seller.money() + userPaymentRequest.withdrawalAmount());

        updateUser(seller);
        updateUser(buyer);
    }

    public void removeCard(Long uid, Long cid) throws UserNotFoundException {
        User user = findUserById(uid);

        List<Card> newCards = user.cardList().stream()
                .filter(card -> !Objects.equals(card.getId(), cid))
                .toList();

        user.setCardList(newCards);

        updateUser(user);
    }

    public void addCard(Long uid, Long cid) throws URISyntaxException, CardNotFoundException, UserNotFoundException {
        User user = findUserById(uid);

        List<Card> newCards = new ArrayList<>(user.cardList());
        newCards.add(cardApi.findCardById(cid));
        user.setCardList(newCards);

        updateUser(user);
    }

    public void getCardFromUser(Long uid, Long cid) throws UserNotFoundException, CardNotOwnedException {
        User user = findUserById(uid);

        List<Card> cards = user.cardList();

        cards = cards.stream().filter(card -> card.getId() == cid)
                .toList();

        if (cards.isEmpty()) throw new CardNotOwnedException();
    }
}
