package cpe.atelier3.market.repository.history;

import cpe.atelier3.commons.market.History;
import cpe.atelier3.commons.market.entity.HistoryEntity;
import cpe.atelier3.commons.market.entity.HistoryEntityMapper;
import cpe.atelier3.market.domain.history.IHistoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HistoryRepository implements IHistoryRepository {

    private final HistoryJpaRepository historyJpaRepository;

    private final HistoryEntityMapper historyEntityMapper;

    public HistoryRepository(HistoryJpaRepository historyJpaRepository, HistoryEntityMapper historyEntityMapper) {
        this.historyJpaRepository = historyJpaRepository;
        this.historyEntityMapper = historyEntityMapper;
    }

    @Override
    public List<History> findAll() {
        List<HistoryEntity> historyEntityList = historyJpaRepository.findAll();

        return historyEntityList.stream()
                .map(historyEntityMapper::historyEntityToHistory)
                .toList();
    }

    @Override
    public void insert(History history) {
        historyJpaRepository.save(historyEntityMapper.historyToHistoryEntity(history));
    }
}
