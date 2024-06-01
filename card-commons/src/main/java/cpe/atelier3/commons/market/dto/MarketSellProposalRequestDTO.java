package cpe.atelier3.commons.market.dto;

public record MarketSellProposalRequestDTO(Long cardToSellId, Long sellerId, long price) {
}