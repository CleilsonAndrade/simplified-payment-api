package br.cleilsonandrade.simplifiedpaymentapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.cleilsonandrade.simplifiedpaymentapi.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findUserByDocument(String document);

  Optional<User> findUserById(Long id);
}
