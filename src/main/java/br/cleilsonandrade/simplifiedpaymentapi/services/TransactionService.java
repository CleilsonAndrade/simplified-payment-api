package br.cleilsonandrade.simplifiedpaymentapi.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.cleilsonandrade.simplifiedpaymentapi.domain.transaction.Transaction;
import br.cleilsonandrade.simplifiedpaymentapi.domain.user.User;
import br.cleilsonandrade.simplifiedpaymentapi.dtos.TransactionDTO;
import br.cleilsonandrade.simplifiedpaymentapi.repositories.TransactionRepository;

@Service
public class TransactionService {
  @Autowired
  private UserService userService;

  @Autowired
  private TransactionRepository transactionRepository;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private NotificationService notificationService;

  public Transaction createTransaction(TransactionDTO transactionDTO) throws Exception {
    User sender = this.userService.findUserById(transactionDTO.senderId());
    User receiver = this.userService.findUserById(transactionDTO.receiverId());

    userService.validateTransaction(sender, transactionDTO.value());

    boolean isAuthorized = this.authorizeTransaction(sender, transactionDTO.value());

    if (isAuthorized) {
      throw new Exception("Transação nao autorizada");
    }

    Transaction newTransaction = new Transaction();
    newTransaction.setAmount(transactionDTO.value());
    newTransaction.setSender(sender);
    newTransaction.setReceiver(receiver);
    newTransaction.setCreatedAt(LocalDateTime.now());

    sender.setBalance(sender.getBalance().subtract(transactionDTO.value()));
    receiver.setBalance((receiver.getBalance().add(transactionDTO.value())));

    this.transactionRepository.save(newTransaction);
    userService.saveUser(sender);
    userService.saveUser(receiver);

    this.notificationService.sendNotification(sender, "Transação realizada com sucesso");

    this.notificationService.sendNotification(receiver, "Transação recebida com sucesso");

    return newTransaction;
  }

  public boolean authorizeTransaction(User sender, BigDecimal value) {
    ResponseEntity<Map> authorizationResponse = restTemplate
        .getForEntity("https://run.mocky.io/v3/827eddf9-d8f1-4810-8c58-1aa5abf9f2da", Map.class);

    if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
      String message = (String) authorizationResponse.getBody().get("message");
      return "Autorizado".equalsIgnoreCase(message);
    } else
      return false;

  }
}
