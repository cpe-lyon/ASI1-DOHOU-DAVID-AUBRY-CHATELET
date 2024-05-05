package cpe.atelier1.controller;

import cpe.atelier1.model.dto.CardFormDTO;
import cpe.atelier1.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RequestCrt {

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


}
