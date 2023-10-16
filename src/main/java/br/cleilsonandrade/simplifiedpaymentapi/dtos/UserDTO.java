package br.cleilsonandrade.simplifiedpaymentapi.dtos;

import java.math.BigDecimal;

import br.cleilsonandrade.simplifiedpaymentapi.domain.user.UserType;

public record UserDTO(String firstName, String lastName, String document, BigDecimal balance, String email,
    String password, UserType userType) {

}
