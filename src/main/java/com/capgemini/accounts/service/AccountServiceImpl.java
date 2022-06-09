package com.capgemini.accounts.service;

import com.capgemini.accounts.client.CustomerFeignClient;
import com.capgemini.accounts.client.TransactionFeignClient;
import com.capgemini.accounts.dto.request.AccountRequest;
import com.capgemini.accounts.dto.request.TransactionRequest;
import com.capgemini.accounts.dto.response.AccountDetailResponse;
import com.capgemini.accounts.dto.response.AccountResponse;
import com.capgemini.accounts.dto.response.BasicResponse;
import com.capgemini.accounts.enums.Status;
import com.capgemini.accounts.exceptions.ResourceNotFoundException;
import com.capgemini.accounts.model.internal.Account;
import com.capgemini.accounts.repository.internal.AccountRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import javax.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

  private final AccountRepository accountRepository;

  private final TransactionFeignClient transactionFeignClient;
  private final CustomerFeignClient customerFeignClient;

  @Value("${capgemini.customer.secret.key}")
  private String secretKey;


  @Override
  public BasicResponse createCurrentAccount(AccountRequest request) {

    log.info("============Creating Account=============");

    BasicResponse verificationResponse = customerFeignClient.validateSecretKey();
    if (verificationResponse.getStatus().equals(Status.SUCCESS)) {

      Account mAccount = saveAccount(request);
      if (request.getInitialCredit() > 0) {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAccountId(mAccount.getAccountId());
        transactionRequest.setAmount(request.getInitialCredit());
        transactionRequest.setCustomerId(request.getCustomerId());

        //Save Transaction
        BasicResponse response = transactionFeignClient.addTransaction(transactionRequest);
        log.info("Response{}", response.getResponseData().toString());
        log.info("Response{}", "initial credit greater than zero");
        //Update Account
        updateAccount(mAccount.getAccountId(), request.getInitialCredit());
        return new BasicResponse(Status.SUCCESS);

      }
      return new BasicResponse(Status.SUCCESS);
    } else {
      return verificationResponse;
    }


  }

  private Account saveAccount(AccountRequest request) {
    Account account = new Account();
    account.setAccountId(UUID.randomUUID().toString());
    account.setBalance((double) 0);
    account.setCustomerId(request.getCustomerId());
    account.setCreatedAt(new Date());
    return accountRepository.save(account);
  }

  public void updateAccount(String accountId, Double amount) {
    Optional<Account> account = accountRepository.findByAccountId(accountId);
    account.ifPresent(value -> value.setBalance(value.getBalance() + amount));
  }

  public Account getAccount(String accountId) {
    Optional<Account> account = accountRepository.findByAccountId(accountId);
    if (account.isPresent()) {
      return account.get();
    } else {
      throw new ResourceNotFoundException("Id not found");
    }
  }

  @Override
  public BasicResponse validateTransactionKey(Map<String, String> requestHeaders) {
    try {
      String secretKeyFromHeader = requestHeaders.get("secret_key");
      if (Objects.isNull(secretKeyFromHeader) || !secretKey.equals(secretKeyFromHeader)) {
        throw new ValidationException("Secret key is not present or not correct!");
      }
      return new BasicResponse(Status.SUCCESS);
    } catch (Exception e) {
      log.error("There was an error while updating customer wallet: {}", e.getMessage());
      return new BasicResponse(Status.FORBIDDEN);
    }
  }

}
