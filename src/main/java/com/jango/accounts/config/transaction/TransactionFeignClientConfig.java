package com.jango.accounts.config.transaction;

import com.jango.accounts.config.customer.CustomerFeignRequestInterceptor;
import com.jango.accounts.helpers.FeignErrorDecoder;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class TransactionFeignClientConfig {

  @Value("${jango.transacton.secret.key}")
  private String secretKey;

  @Bean
  public RequestInterceptor requestInterceptor() {
    return new CustomerFeignRequestInterceptor(secretKey);
  }

  @Bean
  public ErrorDecoder errorDecoder() {
    return new FeignErrorDecoder();
  }

}
