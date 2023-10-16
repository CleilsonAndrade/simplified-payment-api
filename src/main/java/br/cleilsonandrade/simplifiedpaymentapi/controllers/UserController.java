package br.cleilsonandrade.simplifiedpaymentapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.cleilsonandrade.simplifiedpaymentapi.domain.user.User;
import br.cleilsonandrade.simplifiedpaymentapi.dtos.UserDTO;
import br.cleilsonandrade.simplifiedpaymentapi.services.UserService;

@RestController()
@RequestMapping("/users")
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
    User newUser = this.userService.createUser(userDTO);
    return new ResponseEntity<>(newUser, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = this.userService.getAllUsers();

    return new ResponseEntity<>(users, HttpStatus.OK);
  }
}
