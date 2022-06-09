package com.capgemini.accounts.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest {

  @NotBlank(message = "Customer Id cannot be blank")
  private String customerId;
  @NotBlank(message = "Account Id cannot be blank")
  private String accountId;
  @NotBlank(message = "Initial Credit cannot be blank")
  private Double amount;


}
