package br.cleilsonandrade.simplifiedpaymentapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.cleilsonandrade.simplifiedpaymentapi.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
