package com.capgemini.accounts.controller;

import com.capgemini.accounts.dto.request.AccountRequest;
import com.capgemini.accounts.dto.response.BasicResponse;
import com.capgemini.accounts.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @GetMapping("/{customerId}")
  public BasicResponse findAccountsByCustomerId(@PathVariable("customerId") String customerId) {
    return accountService.listAccounts(customerId);
  }


}
