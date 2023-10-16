package br.cleilsonandrade.simplifiedpaymentapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.cleilsonandrade.simplifiedpaymentapi.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
