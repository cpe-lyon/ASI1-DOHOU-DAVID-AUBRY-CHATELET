package cpe.atelier3.commons.user.dto;

import cpe.atelier3.commons.card.dto.CardDTO;

import java.util.List;

public record UserDTO(Long id, String email, String username, String password, double money, List<CardDTO> cards) {
}
