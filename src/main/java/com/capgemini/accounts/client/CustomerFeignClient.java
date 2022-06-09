package com.capgemini.accounts.client;

import com.capgemini.accounts.config.customer.CustomerFeignClientConfig;
import com.capgemini.accounts.dto.response.BasicResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "accounts", url = "${capgemini.customer.base.url}", configuration = CustomerFeignClientConfig.class)
public interface CustomerFeignClient {

  @PostMapping("/api/v1/customer/webhook")
  BasicResponse validateSecretKey();


}
