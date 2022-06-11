package com.capgemini.accounts.service;

import com.capgemini.accounts.client.CustomerFeignClient;
import com.capgemini.accounts.client.TransactionFeignClient;
import com.capgemini.accounts.dto.request.AccountRequest;
import com.capgemini.accounts.dto.request.TransactionRequest;
import com.capgemini.accounts.dto.response.AccountResponse;
import com.capgemini.accounts.dto.response.BasicResponse;
import com.capgemini.accounts.enums.Status;
import com.capgemini.accounts.model.internal.Account;
import com.capgemini.accounts.repository.internal.AccountRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

  private final AccountRepository accountRepository;

  private final TransactionFeignClient transactionFeignClient;
  private final CustomerFeignClient customerFeignClient;



  @Override
  public BasicResponse createCurrentAccount(AccountRequest request) {

    BasicResponse verificationResponse = customerFeignClient.validateSecretKey();
    if (verificationResponse.getStatus().equals(Status.SUCCESS)) {
      if (request.getInitialCredit() > 0) {
        Account mAccount = saveAccount(request);
        addTransaction(request, mAccount);
        return new BasicResponse(Status.SUCCESS, "Account created successfully");

      }
      saveAccount(request);
      return new BasicResponse(Status.SUCCESS, "Account created successfully");
    } else {
      log.info("Error Verifying");
      return verificationResponse;
    }
  }

  private void addTransaction(AccountRequest request, Account mAccount) {
    TransactionRequest transactionRequest = new TransactionRequest();
    transactionRequest.setAccountId(mAccount.getAccountId());
    transactionRequest.setAmount(request.getInitialCredit());
    transactionRequest.setCustomerId(request.getCustomerId());
    //Save Transaction
    transactionFeignClient.addTransaction(transactionRequest);
  }

  private Account saveAccount(AccountRequest request) {
    Account account = new Account();
    account.setAccountId(UUID.randomUUID().toString());
    account.setBalance(request.getInitialCredit());
    account.setCustomerId(request.getCustomerId());
    account.setCreatedAt(new Date());
    return accountRepository.save(account);
  }


  @Override
  public BasicResponse listAccounts(String customerId) {
    BasicResponse verificationResponse = customerFeignClient.validateSecretKey();
    if (verificationResponse.getStatus().equals(Status.SUCCESS)) {
      List<AccountResponse> accountResponses = new ArrayList<>();
      for (Account account : accountRepository.findByCustomerId(customerId)) {
        AccountResponse accountResponse = new ModelMapper().map(account, AccountResponse.class);
        accountResponses.add(accountResponse);

      }
      return new BasicResponse(Status.SUCCESS, accountResponses);
    } else {
      return verificationResponse;
    }

  }

}
