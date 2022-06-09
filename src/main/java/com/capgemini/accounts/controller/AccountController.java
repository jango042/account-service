package com.capgemini.accounts.controller;

import com.capgemini.accounts.dto.request.AccountRequest;
import com.capgemini.accounts.dto.response.BasicResponse;
import com.capgemini.accounts.service.AccountService;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/account")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AccountController {

  private final AccountService accountService;



  @PostMapping("/")
  public BasicResponse createAccount(@RequestBody AccountRequest request) {
    return accountService.createCurrentAccount(request);
  }

  @PostMapping("/transaction/webhook")
  public BasicResponse validateKey(@RequestHeader Map<String, String> requestHeaders) {
    return accountService.validateTransactionKey(requestHeaders);
  }

}
