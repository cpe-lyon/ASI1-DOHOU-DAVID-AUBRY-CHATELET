package cpe.atelier2.repository.history;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "history")
public class HistoryEntity {
    @Id
    private Long id;
    private Long userId;
    private String type;
    private double price;
}
