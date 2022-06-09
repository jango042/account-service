package com.capgemini.accounts.service;

import com.capgemini.accounts.dto.request.AccountRequest;
import com.capgemini.accounts.dto.response.BasicResponse;

public interface AccountService {

  BasicResponse createCurrentAccount(AccountRequest request);
  BasicResponse listAccounts(String customerId);

}
