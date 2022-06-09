package com.capgemini.accounts.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailResponse {

  private String customerId;
  private String accountId;
  private Double balance;

}
