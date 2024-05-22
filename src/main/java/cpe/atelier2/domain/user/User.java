package cpe.atelier2.domain.user;

public record User(Long id, String email, String username, String password, double money) {
}
