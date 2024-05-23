package cpe.atelier2.domain.history;

public record History(Long id, Long cardId, Long userId, String type, double price) {
}
