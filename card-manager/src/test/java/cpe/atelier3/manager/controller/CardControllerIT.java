package cpe.atelier3.manager.controller;

import cpe.atelier3.commons.card.dto.CardDTO;
import cpe.atelier3.manager.ManagerApplicationIT;
import cpe.atelier3.manager.controller.card.CardController;
import cpe.atelier3.manager.domain.card.CardService;
import cpe.atelier3.manager.repository.card.CardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CardControllerIT extends ManagerApplicationIT {

    @Test
    public void findAllShouldReturnAllCardInBase() {

        //given
        List expectedList = List.of(
                new CardDTO(1L, "Métamorph", "Un Pokemon tout mignon",
                "pokemon", "Normal", "https://www.pokepedia.fr/images/e/e3/M%C3%A9tamorph-RFVF.png",
                "https://www.pokepedia.fr/images/e/e3/M%C3%A9tamorph-RFVF.png",25,30,25,40,
                0),
                new CardDTO(2L,"Salameche", "Un Pokemon tout mignon",
                        "pokemon", "Fire",
                        "https://www.pokepedia.fr/images/thumb/8/89/Salam%C3%A8che-RFVF.png/800px-Salam%C3%A8che-RFVF.png",
                        "https://www.pokepedia.fr/images/thumb/8/89/Salam%C3%A8che-RFVF.png/800px-Salam%C3%A8che-RFVF.png",
                        20,60,20,49, 0),
                new CardDTO(3L,"Hericendre", "Un Pokemon tout mignon",
                        "pokemon", "Fire",
                        "https://www.pokepedia.fr/images/thumb/8/86/H%C3%A9ricendre-LPA.png/250px-H%C3%A9ricendre-LPA.png",
                        "https://www.pokepedia.fr/images/thumb/8/86/H%C3%A9ricendre-LPA.png/250px-H%C3%A9ricendre-LPA.png",
                        15,60,10,34, 0),
                new CardDTO(4L,"Carapuce", "Un Pokemon tout mignon",
                        "pokemon", "Water",
                        "https://www.pokepedia.fr/images/thumb/c/cc/Carapuce-RFVF.png/800px-Carapuce-RFVF.png",
                        "https://www.pokepedia.fr/images/thumb/c/cc/Carapuce-RFVF.png/800px-Carapuce-RFVF.png",
                        10,55,20,44, 0),
                new CardDTO(5L,"Pikachu", "Un Pokemon tout mignon",
                        "pokemon", "Electric",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS0-b7rBnwIrsNmEefoGlyynGv1BjF4RbFz4g&usqp=CAU",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS0-b7rBnwIrsNmEefoGlyynGv1BjF4RbFz4g&usqp=CAU",
                        10,55,20,44, 0)
                );
        //when
        List cardDTOList = restTemplate.getForObject(this.uri + "/card/all", List.class);
        //then
        assertThat(expectedList).isNotEqualTo(cardDTOList);
    }
}
