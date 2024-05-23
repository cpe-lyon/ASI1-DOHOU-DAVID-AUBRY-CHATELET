package cpe.atelier2.controller.history;

import cpe.atelier2.domain.history.HistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

    private final HistoryDtoMapper historyDtoMapper;

    private final HistoryService historyService;

    public HistoryController(HistoryDtoMapper historyDtoMapper, HistoryService historyService) {

        this.historyDtoMapper = historyDtoMapper;
        this.historyService = historyService;
    }

    @GetMapping("/all")
    public List<HistoryDTO> findAll() {
        return historyService.findAll().stream()
                .map(historyDtoMapper::historyToHistoryDTO)
                .toList();
    }
}
