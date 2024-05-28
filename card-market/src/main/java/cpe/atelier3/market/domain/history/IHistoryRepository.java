package cpe.atelier3.market.domain.history;

import java.util.List;

public interface IHistoryRepository {

    List<History> findAll();

    void insert(History history);
}
