package cpe.atelier2.repository.history;

import cpe.atelier2.domain.history.History;
import org.springframework.stereotype.Component;

@Component
public class HistoryEntityMapper {

    public History historyEntityToHistory(HistoryEntity historyEntity) {
        return new History(historyEntity.getId(), historyEntity.getCardId(), historyEntity.getUserId(),
                historyEntity.getType(), historyEntity.getPrice());
    }

    public HistoryEntity historyToHistoryEntity(History history) {
        HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setId(null);
        historyEntity.setType(history.type());
        historyEntity.setPrice(history.price());
        historyEntity.setUserId(history.userId());

        return historyEntity;
    }
}
