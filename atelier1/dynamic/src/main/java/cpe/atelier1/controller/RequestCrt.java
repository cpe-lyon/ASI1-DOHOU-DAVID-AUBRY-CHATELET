package cpe.atelier1.controller;

import cpe.atelier1.model.Card;
import cpe.atelier1.model.CardFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RequestCrt {

    @Autowired
    CardDao cardDao;

    @Value("${welcome.message}")
    private String message;

    private static String messageLocal="Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("message", message);
        model.addAttribute("messageLocal", messageLocal);

        return "index";
    }

    @RequestMapping(value = { "/view"}, method = RequestMethod.GET)
    public String view(Model model) {
        model.addAttribute("myPoney", cardDao.getRandomCard() );
        return "poneyView";
    }

    @RequestMapping(value = { "/addPoney"}, method = RequestMethod.GET)
    public String addponey(Model model) {
        CardFormDTO poneyForm = new CardFormDTO();
        model.addAttribute("poneyForm", poneyForm);
        return "poneyForm";
    }

    @RequestMapping(value = { "/addPoney"}, method = RequestMethod.POST)
    public String addponey(Model model, @ModelAttribute("poneyForm") CardFormDTO cardFormDTO) {
        Card p = cardDao.addCard(cardFormDTO.getId(), cardFormDTO.getName(), cardFormDTO.getDescription(),
                cardFormDTO.getFamily(), cardFormDTO.getAffinity(), cardFormDTO.getImgUrl(),
                cardFormDTO.getSmallImgUrl(), cardFormDTO.getEnergy(), cardFormDTO.getHp(),
                cardFormDTO.getDefence(), cardFormDTO.getAttack(), cardFormDTO.getPrice(),
                cardFormDTO.getUserId());
        model.addAttribute("myPoney", p);
        return "poneyView";

    }

    @RequestMapping(value = { "/list"}, method = RequestMethod.GET)
    public String viewList(Model model) {
        model.addAttribute("poneyList", cardDao.getCardList() );
        return "poneyViewList";
    }

}
