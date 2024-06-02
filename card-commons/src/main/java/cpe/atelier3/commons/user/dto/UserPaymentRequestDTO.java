package cpe.atelier3.commons.user.dto;

public record UserPaymentRequestDTO(Long buyerId, Long sellerId, double withdrawalAmount) {
}
