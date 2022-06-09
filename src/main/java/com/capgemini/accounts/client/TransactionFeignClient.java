package com.capgemini.accounts.client;

import com.capgemini.accounts.config.transaction.TransactionFeignClientConfig;
import com.capgemini.accounts.dto.request.TransactionRequest;
import com.capgemini.accounts.dto.response.BasicResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "transactions", url = "${capgemini.transaction.base.url}", configuration = TransactionFeignClientConfig.class)
public interface TransactionFeignClient {

  @PostMapping("/api/v1/transaction/")
  BasicResponse addTransaction(TransactionRequest request);


}
