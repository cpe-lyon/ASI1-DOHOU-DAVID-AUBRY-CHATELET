package cpe.atelier2.controller.market;

public record MarketSellProposalRequestDTO(Long cardToSellId, Long sellerId, long price) {
}