package com.capgemini.accounts.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

  private String firstName;
  private String surname;
  private Double balance;
  private List<AccountDetailResponse> accounts;


}
