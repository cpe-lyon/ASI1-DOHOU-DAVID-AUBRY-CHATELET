package cpe.atelier2.domain.history;

import java.util.List;

public interface IHistoryRepository {

    List<History> findAll();

    void insert(History history);
}
