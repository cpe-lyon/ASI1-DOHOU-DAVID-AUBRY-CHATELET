package cpe.atelier3.market.domain.history;

import cpe.atelier3.commons.market.History;

import java.util.List;

public interface IHistoryRepository {

    List<History> findAll();

    void insert(History history);
}
