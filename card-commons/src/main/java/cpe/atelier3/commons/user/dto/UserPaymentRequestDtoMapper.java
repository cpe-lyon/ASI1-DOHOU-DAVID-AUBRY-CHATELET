package cpe.atelier3.commons.user.dto;

import cpe.atelier3.commons.user.UserPaymentRequest;
import org.springframework.stereotype.Component;

@Component
public class UserPaymentRequestDtoMapper {

    public UserPaymentRequest userPaymentRequestDtoToUserPaymentRequest(UserPaymentRequestDTO paymentRequestDto) {
        return new UserPaymentRequest(paymentRequestDto.buyerId(), paymentRequestDto.sellerId(), paymentRequestDto.withdrawalAmount());
    }

    public UserPaymentRequestDTO userPaymentRequestToUserPaymentRequestDto(UserPaymentRequest userPaymentRequest) {
        return new UserPaymentRequestDTO(userPaymentRequest.buyerId(), userPaymentRequest.sellerId(), userPaymentRequest.withdrawalAmount());
    }
}
