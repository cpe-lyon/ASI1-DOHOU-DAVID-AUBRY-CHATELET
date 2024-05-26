package cpe.atelier2.controller.history;

import cpe.atelier2.domain.history.History;
import org.springframework.stereotype.Component;

@Component
public class HistoryDtoMapper {

    public HistoryDTO historyToHistoryDTO(History history) {
        return new HistoryDTO(history.id(), history.userId(), history.type(), history.price());
    }
}
