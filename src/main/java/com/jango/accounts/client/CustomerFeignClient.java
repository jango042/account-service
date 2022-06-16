package com.jango.accounts.client;

import com.jango.accounts.config.customer.CustomerFeignClientConfig;
import com.jango.accounts.dto.response.BasicResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "accounts", url = "${jango.customer.base.url}", configuration = CustomerFeignClientConfig.class)
public interface CustomerFeignClient {

  @PostMapping("/api/v1/customer/webhook")
  BasicResponse validateSecretKey();


}
