package com.capgemini.accounts.service;

import com.capgemini.accounts.dto.request.AccountRequest;
import com.capgemini.accounts.dto.response.BasicResponse;
import java.util.Map;

public interface AccountService {

  BasicResponse createCurrentAccount(AccountRequest request);
  BasicResponse validateTransactionKey(Map<String, String> requestHeaders);

}
