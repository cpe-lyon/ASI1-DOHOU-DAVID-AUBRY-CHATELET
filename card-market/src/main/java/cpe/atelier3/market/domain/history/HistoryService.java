package cpe.atelier3.market.domain.history;

import cpe.atelier3.commons.market.History;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class HistoryService {

    private final IHistoryRepository iHistoryRepository;

    public HistoryService(IHistoryRepository iHistoryRepository) {
        this.iHistoryRepository = iHistoryRepository;
    }

    //To be called by market service
    public void insert(Long cardId, Long userid, String type, double money) {
        iHistoryRepository.insert(new History(null, cardId, userid, type, money));
    }

    public List<History> findAll() {
        return iHistoryRepository.findAll();
    }
}
