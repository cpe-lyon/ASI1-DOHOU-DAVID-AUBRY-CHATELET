package cpe.atelier2.controller.user;

import cpe.atelier2.controller.card.CardDTO;

import java.util.List;

public record UserDTO(Long id, String email, String username, String password, double money, List<CardDTO> cards) {
}
