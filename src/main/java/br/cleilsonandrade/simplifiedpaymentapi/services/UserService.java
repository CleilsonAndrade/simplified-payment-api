package br.cleilsonandrade.simplifiedpaymentapi.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cleilsonandrade.simplifiedpaymentapi.domain.user.User;
import br.cleilsonandrade.simplifiedpaymentapi.domain.user.UserType;
import br.cleilsonandrade.simplifiedpaymentapi.dtos.UserDTO;
import br.cleilsonandrade.simplifiedpaymentapi.repositories.UserRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  public void validateTransaction(User sender, BigDecimal amount) throws Exception {
    if (sender.getUserType() == UserType.MERCHANT) {
      throw new Exception("Usuário do tipo Lojista nao esta autorizado a realizar transação");
    }

    if (sender.getBalance().compareTo(amount) < 0) {
      throw new Exception("Saldo insuficiente");
    }
  }

  public User findUserById(Long id) throws Exception {
    return this.userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuário nao encontrado"));
  }

  public User createUser(UserDTO data) {
    User newUser = new User(data);
    this.saveUser(newUser);

    return newUser;
  }

  public void saveUser(User user) {
    this.userRepository.save(user);
  }

  public List<User> getAllUsers() {
    return this.userRepository.findAll();
  }
}
