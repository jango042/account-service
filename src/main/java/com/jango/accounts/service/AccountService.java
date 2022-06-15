package com.jango.accounts.service;

import com.jango.accounts.dto.request.AccountRequest;
import com.jango.accounts.dto.response.BasicResponse;

public interface AccountService {

  BasicResponse createCurrentAccount(AccountRequest request);
  BasicResponse listAccounts(String customerId);

}
