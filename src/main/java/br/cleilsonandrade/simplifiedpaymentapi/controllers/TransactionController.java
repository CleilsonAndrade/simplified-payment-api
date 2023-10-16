package br.cleilsonandrade.simplifiedpaymentapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.cleilsonandrade.simplifiedpaymentapi.domain.transaction.Transaction;
import br.cleilsonandrade.simplifiedpaymentapi.dtos.TransactionDTO;
import br.cleilsonandrade.simplifiedpaymentapi.services.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
  @Autowired
  private TransactionService transactionService;

  @PostMapping
  public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception {
    Transaction newTransaction = this.transactionService.createTransaction(transactionDTO);
    return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
  }
}
