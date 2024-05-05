package cpe.atelier1.controller;

import cpe.atelier1.model.Card;
import cpe.atelier1.model.dto.CardFormDTO;
import cpe.atelier1.model.dto.CardSearchDTO;
import cpe.atelier1.services.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RequestCrt {

    Logger logger = LoggerFactory.getLogger(RequestCrt.class);


    @Autowired
    CardService cardService;

    @Value("${welcome.message}")
    private String message;

    private static String messageLocal="Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("message", message);
        model.addAttribute("messageLocal", messageLocal);
        model.addAttribute("cards", cardService.getCards());

        return "new/card";
    }

    @RequestMapping(value = { "/details/{id}"}, method = RequestMethod.GET)
    public String details(Model model, @PathVariable int id) {
        model.addAttribute("card", cardService.getCard(id));

        return "new/details";
    }

    @RequestMapping(value = {"/addCard"}, method = RequestMethod.GET)
    public String cardForm(Model model) {
        model.addAttribute("card", new CardFormDTO());

        return "new/addCard";
    }

    @RequestMapping(value = {"/addCard"}, method = RequestMethod.POST)
    public String cardSubmission(@ModelAttribute CardFormDTO card, Model model) {
        cardService.addCard(card)
                .ifPresent(e -> model.addAttribute("exception", e.getMessage()));

        model.addAttribute("card", new CardFormDTO());
        return "new/addCard";
    }

    @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
    public String search(Model model) {
        model.addAttribute("cardSearch", new CardSearchDTO());
        return "new/searchCard";
    }

    @RequestMapping(value = {"/search"}, method = RequestMethod.POST)
    public String searchResult(@ModelAttribute CardSearchDTO cardSearch, Model model) {
        Card card = cardService.searchCard(cardSearch).orElse(null);

        model.addAttribute("cardSearch", new CardSearchDTO());
        model.addAttribute("card", card);
        return "new/searchCard";
    }
}
