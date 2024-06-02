package cpe.atelier3.commons.user;

public record UserPaymentRequest(Long buyerId, Long sellerId, double withdrawalAmount)  {
}
