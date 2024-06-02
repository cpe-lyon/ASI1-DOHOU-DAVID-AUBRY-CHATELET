package cpe.atelier3.commons.market.entity;

import cpe.atelier3.commons.market.History;
import org.springframework.stereotype.Component;

@Component
public class HistoryEntityMapper {

    public History historyEntityToHistory(HistoryEntity historyEntity) {
        return new History(historyEntity.getId(), historyEntity.getCardId(), historyEntity.getUserId(),
                historyEntity.getType(), historyEntity.getPrice());
    }

    public HistoryEntity historyToHistoryEntity(History history) {
        HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setId(historyEntity.getId());
        historyEntity.setType(history.type());
        historyEntity.setPrice(history.price());
        historyEntity.setUserId(history.userId());

        return historyEntity;
    }
}
